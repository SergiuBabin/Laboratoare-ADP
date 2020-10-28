build:
	gcc oets.c -o oets -lpthread -lm -Wall
	gcc shear.c -o shear -lpthread -Wall
	gcc merge.c -o merge -lpthread -Wall
clean:
	rm -rf oets shear merge
run_1:
	./oets 10000 4
run_2:
	./shear 1000 4
run_3:
	./merge 2048 4