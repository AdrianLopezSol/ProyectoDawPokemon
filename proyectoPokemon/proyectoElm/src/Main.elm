module Main exposing (..)

import Browser
import Http
import Html exposing (Html, button, div, input, text)
import Html.Attributes exposing (class, placeholder, type_)
import Html.Events exposing (onInput, onClick)
import Json.Decode as Decode
import Json.Encode as Encode
import List exposing (filter, member)
import Task exposing (Task)

-- MODEL

type alias Model =
    { selectedTypes : List Int
    , isLoading : Bool
    , page : Int
    , searchTerm : String
    , pokemonList : List Pokemon
    }

type Msg
    = TypeButtonClicked Int
    | PokemonListReceived (Result Http.Error (List Pokemon))
    | SearchBarChanged String
    | ToggleTypeButton Int
    | LoadPokemonData
    | FetchFailed Http.Error

type alias Pokemon =
    { id : Int
    , name : String
    , types : List Type
    }

type alias Type =
    { id : Int
    , name : String
    }

initialModel : Model
initialModel =
    { selectedTypes = []
    , isLoading = False
    , page = 1
    , searchTerm = ""
    , pokemonList = []
    }

-- UPDATE

update : Msg -> Model -> (Model, Cmd Msg)
update msg model =
    case msg of
        ToggleTypeButton typeId ->
            let
                updatedTypes =
                    if List.member typeId model.selectedTypes then
                        List.filter (\t -> t /= typeId) model.selectedTypes
                    else
                        typeId :: model.selectedTypes
            in
            ( { model | selectedTypes = updatedTypes }, Cmd.none )

        SearchBarChanged term ->
            ( { model | searchTerm = term }
            , Cmd.none
            )

        LoadPokemonData ->
            ( { model | isLoading = True }
            , fetchPokemonData
            )

        TypeButtonClicked typeId ->
            let
                updatedTypes =
                    if List.member typeId model.selectedTypes then
                        List.filter (\t -> t /= typeId) model.selectedTypes
                    else
                        typeId :: model.selectedTypes
            in
            -- Clear the Pokemon list and set isLoading to True to indicate loading
            ( { model | selectedTypes = updatedTypes, isLoading = True, pokemonList = [] }, Cmd.none)

        FetchFailed _ -> -- 
            (model, Cmd.none)

        PokemonListReceived (Ok pokemonList) ->
                    ( { model | isLoading = False, pokemonList = pokemonList }, Cmd.none )

        PokemonListReceived (Err _) ->
                    ( { model | isLoading = False }, Cmd.none )

-- FETCH POKEMON DATA
fetchPokemonData : Cmd Msg
fetchPokemonData =
    Http.get
        { url = "http://localhost:8080/api/pokemon"
        , expect = Http.expectJson PokemonListReceived pokemonListDecoder
        }

pokemonListDecoder : Decode.Decoder (List Pokemon)
pokemonListDecoder =
    Decode.list pokemonDecoder

pokemonDecoder : Decode.Decoder Pokemon
pokemonDecoder =
    Decode.map3 Pokemon
        (Decode.field "id" Decode.int)
        (Decode.field "name" Decode.string)
        (Decode.field "types" (Decode.list typeDecoder))

typeDecoder : Decode.Decoder Type
typeDecoder =
    Decode.map2 Type
        (Decode.field "id" Decode.int)
        (Decode.field "name" Decode.string)

-- VIEW

view : Model -> Html Msg
view model =
    div []
        [ div [ class "type-buttons-container" ]
            (List.map (\typeId ->
                button [ onClick (TypeButtonClicked typeId) ] [ text (String.fromInt typeId) ]
            ) [ 1, 2, 3 ] -- Example type IDs
            )
        , div [ class "search-container" ]
            [ input [ type_ "text", placeholder "Search by name...", onInput SearchBarChanged ] []
            ]
        , div [ class "pokemon-list" ]
            (List.map (\pokemon ->
                div [] [ text pokemon.name ]
            ) model.pokemonList
            )
        , div [ class "details-container" ] []
        ]

-- MAIN

main : Program () Model Msg
main =
    Browser.element
        { init = \_ -> (initialModel, Cmd.none)
        , update = update
        , view = view
        , subscriptions = \_ -> Sub.none
        }