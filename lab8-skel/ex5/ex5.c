#include "mpi.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define ROOT 0

int main (int argc, char *argv[])
{
    int  numtasks, rank;

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &numtasks);
    MPI_Comm_rank(MPI_COMM_WORLD,&rank);

    // Checks the number of processes allowed.
    if (numtasks != 2) {
        printf("Wrong number of processes. Only 2 allowed!\n");
        MPI_Finalize();
        return 0;
    }

    // How many numbers will be sent.
    int send_numbers = 10;
    int value;
    int tags;
    if (rank == 0) {
        for (int i = 0; i < send_numbers; i++) {
        // Generate the random numbers.
        value = rand() % 1000;
        // Generate the random tags.
        tags = rand() % 100;
        // Sends the numbers with the tags to the second process.
        printf("Process [%d] send %d.\n", rank, value);
        MPI_Send(&value, 1, MPI_INT, 1, tags, MPI_COMM_WORLD);
        }

    } else {
        for (int i = 0; i < send_numbers; i++) {
        // Receives the information from the first process.
        // Prints the numbers with their corresponding tags.
        MPI_Status status;

        MPI_Recv(&value, 1, MPI_INT, ROOT, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
        printf("Process [%d] receives %d from tag [%d].\n", rank, value, status.MPI_TAG);
        }
    }

    MPI_Finalize();

}

