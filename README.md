## Kompresja-drzew

Task is written in Polish.  

Zadanie domowe – kompresja drzew binarnych  

Kompresją drzewa nazwiemy przekształcenie go w skierowany graf acykliczny (DAG) przez połączenie wszyst-  
kich wystąpień tego samego niepustego poddrzewa w jedno. Napisać program, który wczyta z wejścia tek-  
stową reprezentację drzewa binarnego liczb całkowitych większych od 0 i wypisze na wyjście reprezentację  
wyniku jego kompresji.  

#Postać danych

Puste drzewo jest reprezentowane przez wiersz z liczbą 0 a drzewo niepuste przez ciąg wierszy, z których  
pierwszy zawiera wartość korzenia, w kolejnych jest reprezentacja lewego a po niej prawego poddrzewa.  

#Postać wyniku

Wynik programu otrzymujemy z danych wejściowych zastępując drugie i każde kolejne wystąpienie ciągu  
wierszy reprezentujących takie samo niepuste poddrzewo jednym wierszem. Umieszczamy w nim wartość  
K − N , gdzie N to numer tego wiersza w tekście wynikowym a K to numer wiersza wyniku, w którym  
zaczyna się reprezentacja pierwszego wystąpienia poddrzewa.  

#Przykład

Dla danych (example/in.{png,txt}):  
11  
22  
11  
0  
0  
11  
0  
0  
33  
22  
11  
0  
0  
11  
0  
0  
11  
0  
0  

wynikiem programu powinno być (example/out.{png,txt}):  
11  
22  
11  
0  
0  
-3  
33  
-6  
-6  

Wczytywanie/przetwarzanie drzewa może być wykonane metodą rekurencyjną.

