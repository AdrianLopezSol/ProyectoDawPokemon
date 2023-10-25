// script.js
const pokemonList = document.getElementById("pokemon-list");
const detailsContainer = document.getElementById("details-container");
const pokemonImage = document.getElementById("pokemonImage");

// Fetch Pokémon data from your API
fetch("http://localhost:8080/pokemon")
    .then((response) => response.json())
    .then((result) => {
        const data = result.data;
        data.forEach((pokemon) => {
            const listItem = document.createElement("li");
            listItem.innerText = `${pokemon.id}: ${capitalizeFirstLetter(pokemon.name)}`;
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
        pokemonImage.src = `img/${pokemon.id}.png`;
    }

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }