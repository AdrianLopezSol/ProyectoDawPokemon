const detailsContainer = document.getElementById("details-container");
const typeButtonsContainer = document.getElementById("type-buttons-container");
const searchBar = document.getElementById("search-bar");

let page = 1;
let isLoading = false;
let selectedTypes = []; // Array to store selected type IDs

// Function to load Pokémon data based on selected types and search content
function loadPokemonData() {
  if (isLoading) {
      return;
  }

  isLoading = true;

  let url;

  const searchTerm = searchBar.value.trim().toLowerCase();

  // If there are selected types and search term, fetch all Pokemon and filter client-side
  if (selectedTypes.length > 0 && searchTerm !== "") {
      url = `http://localhost:8080/api/pokemon`;
  } else if (selectedTypes.length === 0 && searchTerm !== "") {
      // If no types are selected but there's a search term, fetch based on the search term
      url = `http://localhost:8080/api/pokemon/name/${searchTerm}`;
  } else if (selectedTypes.length > 0 && searchTerm === "") {
      // If types are selected but no search term, fetch based on types
      const selectedType = selectedTypes[0];
      url = `http://localhost:8080/api/pokemon/types/${selectedType}?page=${page}`;
  } else {
      // If no types and no search term, fetch paginated data
      url = `http://localhost:8080/api/pokemon?page=${page}`;
  }

  fetch(url)
      .then((response) => response.json())
      .then((result) => {
          let data = result.data;

          // Filter by selected types
          if (selectedTypes.length > 0 && searchTerm === "") {
              data = data.filter((pokemon) => {
                  return selectedTypes.every((typeId) => {
                      return pokemon.types.some((type) => type.type.id === typeId);
                  });
              });
          }

          // Filter by name and selected types
          if (searchTerm !== "" && selectedTypes.length > 0) {
              data = data.filter((pokemon) => {
                  return pokemon.name.toLowerCase().includes(searchTerm) &&
                      selectedTypes.every((typeId) => {
                          return pokemon.types.some((type) => type.type.id === typeId);
                      });
              });
          }

          const pokemonList = document.getElementById("pokemon-list");

          // Append new items instead of replacing existing ones
          data.forEach((pokemon) => {
              const listItem = createPokemonListItem(pokemon);
              listItem.addEventListener("click", () => showDetails(pokemon));
              pokemonList.appendChild(listItem);
          });

          isLoading = false;
          if (selectedTypes.length === 0 && searchTerm === "") {
              page++; // Increment page number for next fetch
          }
      })
      .catch((error) => {
          console.error("Error:", error);
          isLoading = false;
      });
}

// Add event listener to the search bar input
searchBar.addEventListener("input", function () {
    page = 1;
    clearPokemonList();
    loadPokemonData();
});

// Function to fetch Pokemon by name
function fetchPokemonByName(name) {
    fetch(`http://localhost:8080/api/pokemon/name/${name}`)
        .then((response) => response.json())
        .then((result) => {
            const data = result.data;
            const pokemonList = document.getElementById("pokemon-list");

            // Clear the existing pokemonList before appending new items
            pokemonList.innerHTML = "";

            data.forEach((pokemon) => {
                const listItem = createPokemonListItem(pokemon);
                listItem.addEventListener("click", () => showDetails(pokemon));
                pokemonList.appendChild(listItem);
            });
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function createPokemonListItem(pokemon) {
    const { id, name, types } = pokemon;

    const listItem = document.createElement("div");
    listItem.className = "pokemon-element";
    listItem.style.display = "block";

    const listTitle = document.createElement("p");
    listTitle.className = "list-title";
    listTitle.textContent = `${id} ${capitalizeFirstLetter(name)}`;
    listItem.appendChild(listTitle);

    const listSprite = document.createElement("div");
    listSprite.className = "list-sprite";
    const spriteImage = createImage(`img/${id}.webp`, name);
    listSprite.appendChild(spriteImage);
    listItem.appendChild(listSprite);

    const listType = document.createElement("div");
    listType.className = "list-type";
    types.forEach((type) => {
        const typeImage = createImage(`img/types/${type.type.id}.webp`);
        listType.appendChild(typeImage);
    });
    listItem.appendChild(listType);

    listItem.dataset.name = name; // Store the name as a data attribute for search filtering

    return listItem;
}

function createImage(src, alt) {
    const image = document.createElement("img");
    image.src = src;
    image.alt = alt;
    return image;
}

function debounce(func, delay) {
    let timer;
    return function () {
        clearTimeout(timer);
        timer = setTimeout(func, delay);
    };
}

const debounceLoadData = debounce(loadPokemonData, 100);

window.addEventListener('scroll', function () {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight * 0.80) {
        debounceLoadData();
    }
});

const detailsModal = document.getElementById("details-modal");
const closeDetailsButton = document.getElementById("close-details");

function showDetails(pokemon) {
    fetch(`http://localhost:8080/api/pokemon/${pokemon.id}`)
        .then((response) => response.json())
        .then((result) => {
            const data = result.data;
            const modal = document.getElementById("pokemon-modal");

            const typeImages = data.types.map((type) => {
                const typeImage = createImage(`img/types/${type.type.id}.webp`);
                return typeImage.outerHTML;
            });

            modal.innerHTML = `
                <div class="modal-content">
                    <span class="close" id="close-details">&times;</span>
                    <div id="content-block1">
                        <h2>${capitalizeFirstLetter(data.name)}</h2>
                        <p>ID: ${data.id}</p>
                        <p>Height: ${(data.height / 10).toFixed(1)}m</p>
                        <p>Weight: ${(data.weight / 10).toFixed(1)}kg</p>
                        <p>Base Experience: ${data.exp}</p>
                        <img src="img/${pokemon.id}.webp" alt="${data.name}" id="pokemon-image">
                        <div id="pokemon-types">
                            ${typeImages.join('')}
                        </div>
                    </div>
                    <div id="pokemon-stats">
                        <h3>Stats:</h3>
                        <p>HP: ${data.stats.hp}</p>
                        <div class="progress-bar" style="background-color: ${getProgressBarColor(data.stats.hp)}; width: ${calculateProgressBarWidth(data.stats.hp)};"></div>
                        <p>Attack: ${data.stats.atk}</p>
                        <div class="progress-bar" style="background-color: ${getProgressBarColor(data.stats.atk)}; width: ${calculateProgressBarWidth(data.stats.atk)};"></div>
                        <p>Defense: ${data.stats.def}</p>
                        <div class="progress-bar" style="background-color: ${getProgressBarColor(data.stats.def)}; width: ${calculateProgressBarWidth(data.stats.def)};"></div>
                        <p>Special Attack: ${data.stats.sp_atk}</p>
                        <div class="progress-bar" style="background-color: ${getProgressBarColor(data.stats.sp_atk)}; width: ${calculateProgressBarWidth(data.stats.sp_atk)};"></div>
                        <p>Special Defense: ${data.stats.sp_def}</p>
                        <div class="progress-bar" style="background-color: ${getProgressBarColor(data.stats.sp_def)}; width: ${calculateProgressBarWidth(data.stats.sp_def)};"></div>
                        <p>Speed: ${data.stats.speed}</p>
                        <div class="progress-bar" style="background-color: ${getProgressBarColor(data.stats.speed)}; width: ${calculateProgressBarWidth(data.stats.speed)};"></div>
                    </div>
                </div>
            `;

            modal.style.display = "flex";

            const closeDetailsButton = document.getElementById("close-details");
            closeDetailsButton.addEventListener("click", function () {
                modal.style.display = "none";
            });

        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

function getProgressBarColor(statValue) {
    if (statValue < 50) {
        return "red";
    } else if (statValue < 100) {
        return "green";
    } else if (statValue < 140) {
        return "lightblue";
    } else {
        return "deepblue";
    }
}

function calculateProgressBarWidth(statValue) {
    return (statValue / 200) * 100 + "%";
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

// Load type buttons
function loadTypeButtons() {
    fetch("http://localhost:8080/api/types")
        .then((response) => response.json())
        .then((result) => {
            const types = result.data;

            types.forEach((type) => {
                if (type.id < 1000) {
                    const button = createTypeButton(type);
                    typeButtonsContainer.appendChild(button);
                }
            });
        })
        .catch((error) => {
            console.error("Error:", error);
        });
}

// Function to create type button
function createTypeButton(type) {
    const button = document.createElement("button");
    button.className = "type-button";
    button.style.backgroundImage = `url('img/types/${type.id}.webp')`;
    button.addEventListener("click", () => toggleTypeButton(button, type.id));
    return button;
}

// Function to toggle type button
function toggleTypeButton(button, typeId) {
    button.classList.toggle("pressed");

    // Update selectedTypes array
    const index = selectedTypes.indexOf(typeId);
    if (index === -1) {
        selectedTypes.push(typeId);
    } else {
        selectedTypes.splice(index, 1);
    }

    // Clear Pokémon list
    clearPokemonList();

    // Reload Pokémon data based on selected types
    page = 1; // Reset page number
    loadPokemonData();
}

// Function to clear Pokémon list
function clearPokemonList() {
    const pokemonList = document.getElementById("pokemon-list");
    pokemonList.innerHTML = "";
}

// Call the function to load type buttons
loadTypeButtons();

window.addEventListener('load', () => {
    loadPokemonData();
});