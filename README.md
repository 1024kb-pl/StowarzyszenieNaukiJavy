# Stowarzyszenie Nauki Javy
Grupa wspólnej nauki ucząca się poprzez rozwiązywanie wylistowanych projektów. W skrócie użytkownicy tworzą projekty, po zrealizowaniu projektu następuje sprawdzanie rozwiązania przez innych uczestników.

# Jak zączać
Aby zacząć naukę nie trzeba do nikogo pisać i prosić o pozwolenie, wystarczy wykonać poniższe kroki.

1. Załóż konto na github.com
2. Zrób fork tego repozytorium.  https://help.github.com/articles/fork-a-repo/#fork-an-example-repository
3. Sklonuj sforkowane repo (ze swojego profilu) na dysk przy użyciu komendy
```
git clone <adres-repo>
```
4. Utwórz swój branch (np. login, imię+nazwisko) komendą:
```
git checkout -b "nazwa-branchu"
```
5. Stwórz w katalogu projektu folder o takiej samej nazwie jak nazwa branchu.
6. W środku stworzonego katalogu utwórz plik o nazwie me.txt, a w nim napisz kilka informacji o sobie. Jeśli nic nie chcesz pisać to zostaw go pusty - WAŻNE ABYŚ GO STWORZYŁ!
7. Wpisz poniższe komendy w konsoli:
```
git add *
git commit -m "Register <twoja-nazwa>"
git push origin --all
```
8. Następnie wejdź na swoje sforkowane repozytorium i przy nazwie swojego brancha kliknij "New Pull Request".

9. Ważne żeby merge był z Twojego brancha na gałąź master. Naciśnij "Create pull request".
10. Po zaakceptowaniu pull requesta jesteś gotowy do rozpoczęcia nauki
11. Po jakimś czasie otrzymasz zaproszenie do repozytorium StowarzyszenieNaukiJavy, przyjęcie go pozwoli Ci oceniać kod innych uczestników

# Jak się uczyć
W repozytorium znajdziesz listę projektów (https://github.com/1024kb-pl/StowarzyszenieNaukiJavy/wiki/Lista-projekt%C3%B3w-do-wykonania), które możesz wykonać. Wybierz sobie jeden z nich i wykonaj następujące kroki:

0. Na początek przeczytaj FAQ: https://github.com/1024kb-pl/StowarzyszenieNaukiJavy/wiki/FAQ
1. Przejdź do swojego katalogu w projekcie (nazwa podczas rejestracji)
2. Utwórz w nim projekt z nazwą projektu jaki wybrałeś z listy
3. Zaimplementuj cały projekt
4. Wypushuj swoje zmiany przy użyciu komendy:
```
git push origin <nazwa-twojego-brancha>
```
Pamiętaj, aby wcześniej zacomitować swoje zmiany (git add + commit).

5. Jeśli czujesz, że projekt jest gotowy to wejdź na swoje sforkowane repozytorium i tak samo jak podczas rejestracji wykonaj Pull Request swojego branchu do mastera.

6. Nadszedł czas sprawdzenia twojego kodu - mogą to robić wszyscy inni uczestnicy grupy. Jeśli otrzymasz co najmniej 3 approve Twój projekt zostanie dołączony do gałęzi master.

7. Gratulację, właśnie ukończyłeś swój pierwszy projekt. Wybierz kolejny i kontynuuj naukę.
