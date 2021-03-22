# Laboratoare-APD
Algoritmi Paraleli și Distribuiți

### Laborator 1. (Introducere în programarea paralelă cu Pthreads)

    * Schimbați numărul de thread-uri din cod astfel încât să fie egal cu numărul de core-uri de pe mașina pe care rulați, astfel încât, rulând codul pe un alt calculator, numărul de thread-uri să se schimbe automat.
    * Modificați funcția f astfel încât mesajul „Hello World” să fie afișat iterativ de 100 de ori de fiecare thread, împreună cu indicele iterației.
    * Modificați programul astfel încât să creeze două thread-uri, fiecare thread rulând propria sa funcție. 
    * Pornind de la codul din fișierul add_serial.c din arhiva de laborator, paralelizați incrementarea elementelor unui vector cu 100. Acest lucru va presupune împărțirea iterației de adunare la toate thread-urile într-un mod cât mai echitabil.
    
### Laborator 2. (Elemente de sincronizare în Pthreads)
  
    * Rezolvați problema de sincronizare folosind un mutex.
    * Pornind de la fișierul barrier.c din arhiva de laborator, folosiți o barieră pentru a vă asigura că output-ul va fi întotdeauna „1\n2”.
    * Pornind de la fișierul multiply.c din arhiva de laborator, paralelizați programul prin împărțirea iterației exterioare la mai multe thread-uri. 
    * Pornind de același cod, paralelizați doar cea de-a doua buclă de iterație.
    * Pornind de la același cod, paralelizați doar bucla interioară.
    * Pornind de la fișierul strassen.c din arhiva de laborator, paralelizați înmulțirea unor matrice cu algoritmul Strassen folosind 7 thread-uri.

### Laborator 3. (Algoritmi paraleli de sortare)
   
    * Implementați și paralelizați algoritmul odd-event transposition sort.
    * Implementați și paralelizați algoritmul shear sort.
    * Implementați și paralelizați algoritmul merge sort.
    
### Laborator 4. (Introducere în Java Multithreading)

    * Creați un program care să lanseze un număr de thread-uri egal cu numărul de core-uri de care dispune calculatorul vostru. Fiecare thread trebuie 
      să afișeze la consolă un text de tipul ”Hello from thread #id”.
    * Rezolvați bug-urile prezente în arhiva de laborator. Folosiți-vă de hint-uri din surse.
    * Paralelizați dublarea elementelor unui vector.
    * Paralelizați algoritmul Floyd-Warshall.

### Laborator 5. (Probleme de sincronizare)

    * Pornind de la scheletul oneProducerOneConsumer furnizat, implementați algoritmul Producer-Consumer pentru un buffer de dimensiune 1.
    * Modificați algoritmul Producer-Consumer astfel încât să accepte mai mulți producători și mai mulți consumatori. De asemenea, modificați
      buffer-ul astfel încât să fie de dimensiune > 1. Porniți de la scheletul multipleProducersMultipleConsumersNBuffer.
    * Rezolvați problema din algoritmul filozofilor (pachetul philosophersProblem) și explicați-o.
    * Implementați căutarea binară paralelă.
    
### Laborator 6. (Structuri și operații atomice în Java)
     
    * Pornind de la arhiva de laborator, rezolvați problema de sincronizare din pachetul multipleProducersMultipleConsumers, folosind ArrayBlockingQueue.
    * Rezolvați problema de sincronizare din pachetul synchronizationProblem, folosind un AtomicInteger.
    * Rezolvați problema de sincronizare din pachetul bugConcurrentHashMap, folosind metode din clasa ConcurrentHashMap.
    * Paralelizați programul din pachetul synchronizedSortedList, unde:
         - trei thread-uri citesc numere din trei fișiere (câte un thread citește câte un fișier) și 
            adaugă numerele din fișiere într-o listă partajată.
         - al patrulea thread sortează lista cu numerele.
         - lista poate fi sortată doar după ce citirea a fost efectuată de către toate cele trei 
            thread-uri care citesc din fișier.
         - sincronizarea între thread-uri trebuie realizată folosind un semafor. 
            
    * Paralelizați programul din pachetul parallelTree, unde:
         - două thread-uri construiesc un arbore binar în paralel, citind ID-urile și părintele nodurilor noi din 
            câte un fișier, și apoi executând operația de inserare.
         - al treilea verifică dacă arborele a fost construit corect.
         - verificarea arborelui nu se poate face decât după finalizarea alcătuirii sale.
         - sincronizarea între thread-uri trebuie realizată folosind o barieră.
         - la inserția în arbore, sincronizarea nu trebuie făcută global (pe tot arborele), ci pentru fiecare nod din arbore.
         
### Laborator 7. (Modelul Replicated Workers)

    * Paralelizați găsirea căilor între două noduri pe baza scheletului oferit (în pachetul task1) folosind ExecutorService.
    * Paralelizați problema colorării unui graf pe baza scheletului oferit (în pachetul task2) folosind ExecutorService.
    * Paralelizați problema damelor pe baza scheletului oferit (în pachetul task3, cu soluțiile 
      [(2, 1), (4, 2), (1, 3), (3, 4)] și [(3, 1), (1, 2), (4, 3), (2, 4)]) folosind ExecutorService.
    * Paralelizați găsirea căilor între două noduri pe baza scheletului oferit (în pachetul task4) folosind ForkJoinPool.
    * Paralelizați problema colorării unui graf pe baza scheletului oferit (în pachetul task5) folosind ForkJoinPool.
    * Paralelizați problema damelor pe baza scheletului oferit (în pachetul task6) folosind ForkJoinPool.
    
### Laborator 8. (Introducere în programarea distribuită cu MPI)

    * Implementați algoritmul inel folosind MPI.
    * Implementați un program în MPI în care procesul 0 trimite o valoare generată aleatoriu către celelalte procese, folosind MPI_Bcast.
    * Implementați un program în MPI cu N procese, în care procesul 0 inițializează un array de dimensiune 5 * N cu 0, îl împarte 
      și îl trimite tuturor proceselor cu MPI_Scatter.
    * Implementați un program MPI cu 4 procese, unde 3 procese trimit o valoare către al patrulea proces, care va primi valoarea 
      folosind MPI_ANY_SOURCE și va afișa valoarea primită și rangul procesului sursă folosind MPI_SOURCE din MPI_Status (status din MPI_Recv).
    * Implementați un program MPI cu 2 procese, unde procesul 0 trimite 10 valori cu tag-uri diferite către procesul 1, iar procesul 1 primește 
      valorile folosind parametrul MPI_ANY_TAG în MPI_Recv. Afișați valoarea primită împreună cu tag-ul acesteia folosind MPI_Status.
    * Se pornește un număr de procese N divizibil cu 4. Se împart procesele în grupe de câte 4 și își trimit în cerc (inel) rangul.
    
### Laborator 9. (Operații și prelucrări distribuite în MPI)

    * Implementați operația de reduction, unde fiecare proces are o valoare proprie, prin care avem o colecție de valori (a nu se folosi MPI_Reduction).
    * Implementați operația de scan, unde fiecare proces are o valoare proprie, prin care avem o colecție de valori (a nu se folosi MPI_Scan).
    * Implementați operația de broadcast, prin care procesul 0 trimite o valoare tuturor proceselor (a nu se folosi MPI_Bcast).
    * Implementați operația de calcul polinomial folosind pipeline, pentru f(5), unde f(x) reprezintă ecuația polinomială.
    * Implementați sortarea prin pipeline, folosind scheletul de laborator și explicațiile din laborator și slide-uri.
    * Să se sorteze un vector folosindu-se algoritmul Rank Sort.
### Laborator 10.  
	* Aflati nodul lider al clusterului folosind un algoritm heartbeat (3p)
	* Realizati arborele de acoperire, plecand din lider, folosind un algoritm unda-ecou (3p)
	* Asigurati-va ca numarul de elemente din arborele de acoperire a fost stabilit bine prin calcularea numarului de noduri folosind un algoritm epidemic (3p)
	* Folosind arborele de acoperire, trimiteti catre lider configuratia fiecarui nod si realizati in lider matricea de topologie a clusterului. Distribuiti topologia catre toate nodurile (1p)