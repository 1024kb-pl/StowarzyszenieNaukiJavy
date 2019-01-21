# ProgrammingLearning
Grupa wspólnej nauki poprzez rozwiązywanie wylistowanych projektów. W skrócie użytkownicy tworzą projekty, po zrealizowaniu projektu następuje sprawdzanie rozwiązania przez innych uczestników.

# Jak zączać
Aby zacząć naukę nie trzeba do nikogo pisać i prosić o pozwolenie, wystarczy wykonać poniższe kroki.

1. Załóż konto na github.com
2. Zrób fork tego repozytorium.
3. Sklonuj repozytorium na dysk komenda:
```
git clone https://github.com/1024kb-pl/ProgrammingLearning.git
```
4. Utwórz swój branch (np. login, imię+nazwisko) komendą:
```
git -b checkout "nazwa-branchu"
```
5. Stwórz w katalogu projektu folder o takiej samej nazwie jak nazwa branchu
6. Wpisz poniższe komendy w konsoli:
```
git add *
git commit -m "Register <twoja-nazwa>"
git push origin --all
```
7. Następnie wejdź na stronę https://github.com/1024kb-pl/ProgrammingLearning/pulls i stwórz Pull Request ze swojego branchu do mastera
8. Po zaakceptowaniu pull requesta jesteś gotowy do rozpoczęcia nauki

# Jak się uczyć
W repozytorium znajdziesz listę projektów, które możesz wykonać. Wybierz sobie jeden z nich i wykonaj następujące kroki:

1. Przejdź do swojego katalogu w projekcie (nazwa podczas rejestracji)
2. Utwórz w nim projekt z nazwą projektu jaki wybrałeś z listy
3. Zaimplementuj cały projekt
4. Wypushuj swoje zmiany przy użyciu komendy:
```
git push origin --all
```
Pamiętaj, aby wcześniej zacomitować swoje zmiany (git add + commit).
5. Jeśli czujesz, że projekt jest gotowy to wejdź na stronę https://github.com/1024kb-pl/ProgrammingLearning/pulls i tak samo jak podczas rejestracji wykonaj Pull Request swojego branchu do mastera.
6. Nadszedł czas sprawdzenia twojego kodu - mogą to robić wszyscy inni uczestnicy grupy. Jeśli otrzymasz co najmniej 3 approve Twój projekt zostanie dołączony do gałęzi master.
7. Właśnie ukończyłeś projekt. Wybierz kolejny i kontynuuj naukę.
