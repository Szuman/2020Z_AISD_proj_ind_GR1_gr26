# 2020Z_AISD_proj_ind_GR1_gr26
## Opis sytuacji geopolitycznej
Polska. Rok 2025. Kraj, po wypędzeniu poza swoje granice złowrogiego koronawirusa, został ponownie dotknięty pandemią. Tym razem udało się wykorzystać doświadczenia z przeszłości, co zaowocowało opracowaniem receptury na szczepionkę o charakterze leczniczym (podawanej zarówno chorym jak i zdrowym obywatelom).

## Opis problemu
Apteki, będące źródłem dystrybucji detalicznej, mają możliwość wyboru dostawcy. Dostawcy jednak, czując możliwość wzbogacenia się, ustalają różne ceny za szczepionkę.
Zadaniem grupy menadżerskiej GM sieci aptek jest minimalizacja kosztów sprzedaży szczepionek przy jednoczesnym zapewnieniu dostaw do wszystkich aptek.

GM, dla swojego zespołu analityków ZA, dostarcza plik z wymaganiami dotyczącymi zapotrzebowania w poszczególnych aptekach. Zadaniem ZA jest znalezienie takiej konfiguracji, w której koszty sprzedaży będą najmniejsze.

Uwaga! Plik z wymaganiami jest dostarczany przez GM, a to oznacza, że może zawierać błędy, dlatego ZA musi weryfikować poprawność pliku otrzymanego od GM.

## Przykładowa zawartość pliku dostarczonego przez GM do ZA.
```
# Producenci szczepionek (id | nazwa | dzienna produkcja)
0 | BioTech 2.0 | 900
1 | Eko Polska 2020 | 1300
2 | Post-Covid Sp. z o.o. | 1100
# Apteki (id | nazwa | dzienne zapotrzebowanie)
0 | CentMedEko Centrala | 450
1 | CentMedEko 24h | 690
2 | CentMedEko Nowogrodzka | 1200
# Połączenia producentów i aptek (id producenta | id apteki | dzienna maksymalna liczba dostarczanych szczepionek | koszt szczepionki [zł] )
0 | 0 | 800 | 70.5
0 | 1 | 600 | 70
0 | 2 | 750 | 90.99
1 | 0 | 900 | 100
1 | 1 | 600 | 80
1 | 2 | 450 | 70
2 | 0 | 900 | 80
2 | 1 | 900 | 90
2 | 2 | 300 | 100
```
Wynikiem przekazanym od ZA do GM powinien być zestaw informacji (plik) -- przykład pokazano poniżej.
```
BioTech 2.0     -> CentMedEko Centrala [Koszt = 300 * 70.5 = 21150 zł]
Eko Polska 2020 -> CentMedEko Centrala [Koszt = 150 * 100 = 15000 zł]
/*
...
kolejne wiersze opisujące ustalone połączenia pomiędzy producentami a aptekami
...
*/
Opłaty całkowite: 36150 zł
```
