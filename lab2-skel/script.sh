#!/bin/bash
NAME=${1?Error: no name of function given}
nr=${2:-1} 
i=0; while [ $((i+=1)) -le $nr ]; do ./$NAME; done