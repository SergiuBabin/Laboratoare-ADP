#!/bin/bash

N=10

if [ ! -f "strassen_seq" ]
then
    echo "Nu exista binarul strassen_seq"
    exit
fi

if [ ! -f "strassen" ]
then
    echo "Nu exista binarul strassen"
    exit
fi

./strassen_seq $N > seq.txt
./strassen $N > par.txt

diff seq.txt par.txt

rm -rf seq.txt par.txt
