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
    const { id, name, type_id1, type_id2 } = pokemon;
  
    const listItem = document.querySelector(".pokemon-element").cloneNode(true);
    listItem.style.display = "block";
  
    const listTitle = listItem.querySelector(".list-title");
    listTitle.textContent = `${id} ${capitalizeFirstLetter(name)}`;
  
    const listSprite = listItem.querySelector(".list-sprite");
    const spriteImage = createImage(`img/${id}.webp`, name);
    listSprite.appendChild(spriteImage);
  
    const listType = listItem.querySelector(".list-type");
    const typeImages = [createImage(`img/types/${type_id1}.webp`, pokemon.type1)];
  
    if (type_id2 !== 0) {
      typeImages.push(createImage(`img/types/${type_id2}.webp`, pokemon.type2));
    }
  
    typeImages.forEach((typeImage) => listType.appendChild(typeImage));
  
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
        const modalName = document.getElementById("pokemon-name");
        const modalID = document.getElementById("pokemon-id");
        const modalHeight = document.getElementById("pokemon-height");
        const modalWeight = document.getElementById("pokemon-weight");
        const modalExp = document.getElementById("pokemon-base-experience");
        const modalImage = document.getElementById("pokemon-image");
        const modalTypes = document.getElementById("pokemon-types");

  
        modalName.textContent = capitalizeFirstLetter(data.name);
        modalID.textContent = data.id;
        modalHeight.textContent = data.height / 10 + "m";
        modalWeight.textContent = data.weight / 10 + "kg";
        modalExp.textContent = data.exp;
        modalImage.src = `img/${pokemon.id}.webp`;

        modalTypes.innerHTML = "";

        const typeImages = [createImage(`img/types/${data.type_id1}.webp`, data.type1)];

        if (data.type_id2 !== 0) {
            typeImages.push(createImage(`img/types/${data.type_id2}.webp`, data.type2));
        }

        typeImages.forEach((typeImage) => modalTypes.appendChild(typeImage));
    
            detailsModal.style.display = "block";
        });
  }
  
  closeDetailsButton.addEventListener("click", function () {
    detailsModal.style.display = "none";
  });

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }