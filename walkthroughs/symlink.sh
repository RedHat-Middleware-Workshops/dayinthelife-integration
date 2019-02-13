#!/usr/bin/env bash

array=("lab01" "lab02" "lab03" "lab04" "lab05" "lab06" "lab07" "lab08")

for i in "${array[@]}"
do
  ln -s ../docs/labs/citizen-integrator-track/$i citizen-$i
done