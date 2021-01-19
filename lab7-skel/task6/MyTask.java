package task6;

#include <cstdlib>
#include <iomanip>
#include <iostream>
#include <fstream>
#include <mpi.h>
#include <vector>
# define MASTER 0

using namespace std;

int getNrFromType(string type) {
    if (!type.compare("horror")) {
        return 1;
    } else if (!type.compare("comedy")){
        return 2;
    } else if (!type.compare("fantasy")) {
        return 3;
    } else {
        return 4;
    }
} 

string getTypeFromNr(int nr) {
    if (nr == 1) {
        return "horror";
    } else if (nr == 2){
        return "comedy";
    } else if (nr == 3) {
        return "fantasy";
    } else {
        return "science-fiction";
    }
}

bool isAlphabetCharacter(char a) {
    return ((a <= 122) && (a >= 97));
}

void reverse_String(string& greet, long i, long n){

  if ( n <= i)
    return;

  swap(greet[i], greet[n]);
  reverse_String(greet, i+1, n-1);
}

int main(int argc, char *argv[]) {
    int count;
    int count2;
    int dest;
    int i;
    int id;
    int ierr;
    int p;
    int source;
    MPI_Status status;
    int tag;

    //
    //  Initialize MPI.
    //

    ierr = MPI_Init(&argc, &argv);

    if (ierr != 0) {
        cout << "Fatal Error\n";
        exit ( 1 );
    }

    //  Get the number of processes.

    ierr = MPI_Comm_size(MPI_COMM_WORLD, &p);

    //  Determine this process's rank.

    ierr = MPI_Comm_rank(MPI_COMM_WORLD, &id);

    if (id == MASTER) {
        string myText;
        string text;
        string file = argv[1];
        cout << file;
        int k = 1;
        ifstream MyReadFile(file);
        getline (MyReadFile, myText);
        string type = myText;

        while (getline (MyReadFile, myText)) {
            if (myText == "") {
               text = text + myText + "\n"; 
            } else {
                text = text + myText + " \n"; 
            }
            

            if (!myText.compare("")) {
                // cout << "============================================="<<"\n";
                // cout << "============================================="<<"\n";
                // cout << "============================================="<<"\n";
                // cout << "============================================="<<"\n";
                // cout << text;
                MPI_Send(text.c_str(), text.size()+1, MPI_CHAR, getNrFromType(type), k, MPI_COMM_WORLD);

                k++;
                text.clear();
                getline (MyReadFile, type);
            }
        }

        MPI_Send(&text[0], text.length()+1, MPI_CHAR, getNrFromType(type), k, MPI_COMM_WORLD);

        for (int i = 1; i <= 4; i++) {
            MPI_Send(&text[0], text.length()+1, MPI_CHAR, i, 0, MPI_COMM_WORLD);
        }

        text.clear();
       
        MyReadFile.close();

        file.resize(file.size() - 3);
        ofstream MyFile(file+"out");

        vector<pair<string, int>> v;
        string recv_text;
        for (int i = 1; i <= k; i++) {
            //cout << "dasdsadsa\n";
            MPI_Probe(MPI_ANY_SOURCE, i, MPI_COMM_WORLD, &status);
            int count;
            MPI_Get_count(&status, MPI_CHAR, &count);

            char *recv_text = new char[count];
            MPI_Recv(recv_text, count, MPI_CHAR, MPI_ANY_SOURCE, i, MPI_COMM_WORLD, &status);
            

            string stext = recv_text;
            v.push_back(make_pair(stext, status.MPI_SOURCE));

            
            delete [] recv_text;
        }

        for (int i = 0; i < v.size(); i++) {
            // cout << getTypeFromNr(v[i].second)+"\n";
            // cout << v[i].first;
            MyFile << getTypeFromNr(v[i].second)+"\n";
            MyFile << v[i].first;
        }

        //MyFile << "\n";
        MyFile.close();
    } else {
        vector<pair<string, int>> v;
        while (1) { 
            int count;
            MPI_Probe(0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
            MPI_Get_count(&status, MPI_CHAR, &count);
            char *recv_text = new char[count];

            MPI_Recv(recv_text, count, MPI_CHAR, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
            
            

            string send_text(recv_text, count);
            
            if (status.MPI_TAG == 0) {
                for (int i = 0; i < v.size(); i++) {
                    MPI_Send(v[i].first.c_str(), v[i].first.size()+1, MPI_CHAR, 0, v[i].second, MPI_COMM_WORLD);
                    //cout << v[i].second;
                }
                break;
            } else {
            if (id == 1) {
                string consonants = "bcdgfhjklmnpqrstvwxzy";
                for (long i = 0; i < send_text.length(); i++) {
                    if (consonants.find(send_text[i]) != std::string::npos) { 
                        send_text.insert(i+1, 1, send_text[i]); 
                        i++;
                    } else if (consonants.find(send_text[i] + 32) != std::string::npos){
                        send_text.insert(i+1, 1, send_text[i] + 32); 
                        i++;
                    }
                }
            } else if (id == 2) {
                long j = 1;
                for (long i = 0; i < send_text.length(); i++) {

                    if (send_text[i] == ' ') {
                        j = 1;
                        i++;
                    }

                    if (send_text[i] == '\n') {
                        j = 1;
                        i++;
                    }

                    if (j % 2 == 0 && isAlphabetCharacter(send_text[i])) { 
                        send_text[i] = send_text[i] - 32;
                    }
                    j++;
                }
            } else if (id == 3) {
                if (isAlphabetCharacter(send_text[0])) {
                    send_text[0] = send_text[0] - 32;   
                }

                for (int i = 1; i < send_text.length(); i++) {
                    if (isAlphabetCharacter(send_text[i]) && (send_text[i - 1] == ' ' || send_text[i - 1] == '\n')) {
                        send_text[i] = send_text[i] - 32;
                    }
                }
            } else if (id == 4) {
                long nrOfWords = 0;
                long nrOfChar = 0;
                for (long i = 0; i < send_text.length(); i++) {
                    if (send_text[i] == '\n') {
                        nrOfWords = 0;
                    }

                    if (send_text[i] == ' ') {
                        nrOfWords++;
                        if (nrOfWords % 7 == 0){
                            reverse_String(send_text, i - nrOfChar, i-1);
                        } 
                        nrOfChar = 0;
                    } else {
                        nrOfChar++;
                    }
                }
                //cout << send_text;
            }


                v.push_back(make_pair(send_text, status.MPI_TAG));

            
                delete [] recv_text;
            }
        }
    }


    MPI_Finalize();
    
    
    return 0;
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MyTask extends RecursiveTask<Void> {
	int[] graph;
	int step;

	public MyTask(int[] graph, int step) {
		this.graph = graph;
		this.step = step;
	}

	@Override
	protected Void compute() {
		if (Main.N == step) {
			Main.printQueens(graph);
		} else {
			List<MyTask> tasks = new ArrayList<>();
			for (int i = 0; i < Main.N; ++i) {
				int[] newGraph = graph.clone();
				newGraph[step] = i;

				if (Main.check(newGraph, step)) {
					//queens(newGraph, step + 1);
					MyTask t = new MyTask(newGraph, step + 1);
					tasks.add(t);
					t.fork();
				}
			}

			for (MyTask task : tasks) {
				task.join();
			}
		}
		return null;
	}
}
