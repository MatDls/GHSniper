# GHSniper
This program will follow Git Hub repositories and create graph

Wymagania:
- serwis do rejestrowania/ modyfikowania / usuwania / zwracania uczestnikow zarejestrowanych w systemie.
  jako minimum chcemy rejestrowac id, Imie, Nazwisko oraz nazwe repozytorium na Github

- serwis do rejestrowania / modyfikowania / usuwania / zwracania zadan. 
  Kazde zadanie powinno miec id oraz wymagana nazwe brancha na github

- serwis ktory przyjmuje jako input liste uczestnikow i liste zadan dla ktorych ma sprawdzic status na github. 
  Sprawdzenie ograniczamy w tej wersji do sprawdzenia czy istenieje PR z brancha 
  o nazwie zdefiniowanej w zadaniu na koncie uczestnika zarejestrowanym w user.
  Jak tak to sprawdzamy czy jest merged. 
  Ten serwis w odpowiedzi powinien zwracac dla kazdego uczestnika liste zaliczonych zadan (lub nie zaliczonych) -
  czyli status dla kazdego zadania ktorego dotyczylo zapytanie

- dane o uczestnikach i zadaniach powinny byc trzymane w bazie SQL

- dane z 3 serwisu powinny zostac zareprezentowane w prostej aplikacji napisanej w Angular JS. 
  Nie ma potrzeby tworzenia ekranow do dodawania zadan / uczestnikow. 
  Choc warto to zrobic gdy juz pozostale zadania beda gotowe.

STARTED ON 4.02

https://developer.github.com/v3/guides/getting-started/
https://curl.haxx.se/download.html

Important: The default version of the API may change in the future.
If you're building an application and care about the stability of the API,
be sure to request a specific version in the Accept header as shown in the examples below.

**************************************
#   application/vnd.github.v3+json   #
**************************************

https://www.youtube.com/watch?v=Q-BpqyOT3a8
This above is an exa

https://api.github.com/repos/MatDls/GHSniper/pulls{/number}