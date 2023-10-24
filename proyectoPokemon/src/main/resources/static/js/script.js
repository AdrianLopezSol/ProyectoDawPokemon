// script.js
const pokemonList = document.getElementById("pokemon-list");
const detailsContainer = document.getElementById("details-container");
const pokemonImage = document.getElementById("pokemonImage");

// Fetch Pokémon data from your API
fetch("http://localhost:8080/pokemon")
    .then((response) => response.json())
    .then((data) => {
        data.forEach((pokemon) => {
            const listItem = document.createElement("li");
            listItem.innerText = `${pokemon.id}: ${pokemon.name}`;
            listItem.addEventListener("click", () => showDetails(pokemon));
            pokemonList.appendChild(listItem);
        });
    })
    .catch((error) => console.error("Error:", error));

    function showDetails(pokemon) {
        detailsContainer.style.display = "block";
        document.getElementById("pokemon-name").textContent = pokemon.name;
        document.getElementById("pokemon-id").textContent = pokemon.id;
        document.getElementById("pokemon-height").textContent = pokemon.height;
        document.getElementById("pokemon-weight").textContent = pokemon.weight;
        document.getElementById("pokemon-base-experience").textContent = pokemon.base_experience;
    
        // Set the image source based on the ID
        pokemonImage.src = `img/${pokemon.id}.png`;
    }