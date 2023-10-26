// script.js
const detailsContainer = document.getElementById("details-container");
const pokemonImage = document.getElementById("pokemonImage");

fetch("http://localhost:8080/pokemon")
    .then((response) => response.json())
    .then((result) => {
        const data = result.data;
        const pokemonList = document.getElementById("pokemon-list");

        data.forEach((pokemon) => {
            const listItem = document.querySelector(".pokemon-element").cloneNode(true);
            listItem.style.display = "block";

            const listTitle = listItem.querySelector(".list-title");
            const listSprite = listItem.querySelector(".list-sprite");
            const listType = listItem.querySelector(".list-type");

            listTitle.textContent = `${pokemon.id} ${capitalizeFirstLetter(pokemon.name)}`;
            
            const spriteImage = document.createElement("img");
            spriteImage.src = `img/${pokemon.id}.webp`;
            spriteImage.alt = pokemon.name;
            listSprite.appendChild(spriteImage);
            
            const typeImages = [];

            typeImages.push(document.createElement("img"));
            typeImages[0].src = `img/types/${pokemon.type_id1}.webp`;
            typeImages[0].alt = pokemon.type1;
            
            if (pokemon.type_id2 !== 0) {
                typeImages.push(document.createElement("img"));
                typeImages[1].src = `img/types/${pokemon.type_id2}.webp`;
                typeImages[1].alt = pokemon.type2;
            }

            typeImages.forEach(typeImage => {
                listType.appendChild(typeImage);
            });

            listItem.addEventListener("click", () => showDetails(pokemon));
            pokemonList.appendChild(listItem);
        });
    })
    .catch((error) => console.error("Error:", error));

    function showDetails(pokemon) {
        fetch("http://localhost:8080/pokemon/"+pokemon.id)
        .then((response) => response.json())
        .then((result) => {
                const data = result.data;
                detailsContainer.style.display = "block";
                document.getElementById("pokemon-name").textContent = capitalizeFirstLetter(data.name);
                document.getElementById("pokemon-id").textContent = data.id;
                document.getElementById("pokemon-height").textContent = data.height/10 + "m";
                document.getElementById("pokemon-weight").textContent = data.weight/10 + "kg";
                document.getElementById("pokemon-base-experience").textContent = data.exp;
            });
        
        // Set the image source based on the ID
        pokemonImage.src = `img/${pokemon.id}.webp`;
    }

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }