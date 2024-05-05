const detailsContainer = document.getElementById("details-container");
const pokemonImage = document.getElementById("pokemonImage");

let page = 1; // Assuming page starts from 1
let isLoading = false;

function loadPokemonData() {
    if (isLoading) return;
    isLoading = true;

    fetch(`http://localhost:8080/api/pokemon?page=${page}`)
    .then((response) => response.json())
    .then((result) => {
        const data = result.data;
        const pokemonList = document.getElementById("pokemon-list");

        if (!data || data.length === 0) {
            console.error("Error: No data found.");
            isLoading = false;
            return;
        }

        data.forEach((pokemon) => {
            const listItem = createPokemonListItem(pokemon);
            listItem.addEventListener("click", () => showDetails(pokemon));
            pokemonList.appendChild(listItem);
        });

        // Update page number based on PaginationData
        const nextPageUrl = result.PaginationData.next;
        const prevPageUrl = result.PaginationData.previous;
        if (nextPageUrl) {
            const urlParams = new URLSearchParams(nextPageUrl.split("?")[1]);
            page = parseInt(urlParams.get("page"));
        }

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
      const typeImage = createImage(`img/types/${type.type.id}.webp`);
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
  
  function debounce(func, delay) {
    let timer;
    return function () {
      clearTimeout(timer);
      timer = setTimeout(func, delay);
    };
  }
  
  const debounceLoadData = debounce(loadPokemonData, 100);
  
  window.addEventListener('scroll', function () {
    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight*0.80) {
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

            // Map types to type images
            const typeImages = data.types.map((type) => {
                const typeImage = createImage(`img/types/${type.type.id}.webp`);
                return typeImage.outerHTML;
            });

            // Display modal content
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