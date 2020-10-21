build:
	gcc mutex.c -o mutex -lpthread -Wall
	gcc barrier.c -o barrier -lpthread -Wall
	gcc multiply_seq.c -o multiply_seq -lpthread -Wall
	gcc multiply.c -o multiply -lpthread -Wall
	gcc multiply2.c -o multiply2 -lpthread -Wall
	gcc multiply3.c -o multiply3 -lpthread -Wall
	gcc strassen.c -o strassen -lpthread -Wall
	gcc strassen_seq.c -o strassen_seq -lpthread -Wall
clean:
	rm -rf mutex barrier multiply_seq multiply multiply2 multiply3 strassen strassen_seq
run_1:
	./script.sh mutex 20
run_2:
	./script.sh barrier 20
run_3:
	./script.sh test3.sh
run_4:
	./script.sh test4.sh
run_5:
	./script.sh test5.sh
run_6:
	./script.sh test6.sh