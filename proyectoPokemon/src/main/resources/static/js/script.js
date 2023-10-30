// script.js
const detailsContainer = document.getElementById("details-container");
const pokemonImage = document.getElementById("pokemonImage");

let page = 1; // Initialize the page number
let isLoading = false;

function loadPokemonData() {
    if (isLoading) {
      return; // Data is already being loaded, prevent duplicate requests
    }
  
    isLoading = true;
  
    fetch(`http://localhost:8080/pokemon?page=${page}`)
      .then((response) => response.json())
      .then((result) => {
        const data = result.data;
        const pokemonList = document.getElementById("pokemon-list");
  
        data.forEach((pokemon) => {
            const listItem = createPokemonListItem(pokemon);
            listItem.addEventListener("click", () => showDetails(pokemon));
            pokemonList.appendChild(listItem);
        });
  
        page++;
        isLoading = false;
      })
      .catch((error) => {
        console.error("Error:", error);
        isLoading = false;
      });
  }
  
  loadPokemonData();

  function createPokemonListItem(pokemon) {
    const { id, name, types } = pokemon;
  
    const listItem = document.querySelector(".pokemon-element").cloneNode(true);
    listItem.style.display = "block";
  
    const listTitle = listItem.querySelector(".list-title");
    listTitle.textContent = `${id} ${capitalizeFirstLetter(name)}`;
  
    const listSprite = listItem.querySelector(".list-sprite");
    const spriteImage = createImage(`img/${id}.webp`, name);
    listSprite.appendChild(spriteImage);
  
    const listType = listItem.querySelector(".list-type");
  
    types.forEach((type) => {
      const typeImage = createImage(`img/types/${type.id}.webp`);
      listType.appendChild(typeImage);
    });
  
    return listItem;
  }
  
  function createImage(src, alt) {
    const image = document.createElement("img");
    image.src = src;
    image.alt = alt;
    return image;
  }
  
  // Debounce function to prevent multiple rapid scroll events
  function debounce(func, delay) {
    let timer;
    return function () {
      clearTimeout(timer);
      timer = setTimeout(func, delay);
    };
  }
  
  const debounceLoadData = debounce(loadPokemonData, 100); // Adjust the delay as needed
  
  window.addEventListener('scroll', function () {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight*0.50) {
      debounceLoadData(); // Debounced loading of more data
    }
  });

  const detailsModal = document.getElementById("details-modal");
  const closeDetailsButton = document.getElementById("close-details");
  
  function showDetails(pokemon) {
    fetch(`http://localhost:8080/pokemon/${pokemon.id}`)
      .then((response) => response.json())
      .then((result) => {
        const data = result.data;
        const modal = document.getElementById("pokemon-modal");
        const typesImg = document.getElementById("pokemon-types");
  
        const typeImages = data.types.map((type) => {
          const typeImage = createImage(`img/types/${type.id}.webp`);
          return typeImage.outerHTML; // Get the HTML of the image element
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