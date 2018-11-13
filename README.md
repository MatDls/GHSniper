# training-support

  Application:GHSniper
  Author: Mateusz Dzielinski

  This program will follow Git Hub repositories and create graphicall summary of
  their status.

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


****************************************************************
TO DO LIST
-zaaplikować webHook do odświerzania statusów zadań/sprawdzeń
-logowanie użytkowników z użyciem GitHub auth?--na później
(p) test paragrafu
