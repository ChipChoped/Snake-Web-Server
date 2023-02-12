#!/bin/bash
cd "$1" || { echo "Failure: Path doesn't exist"; exit 1; }
mkdir .snake
# shellcheck disable=SC2164
cd .snake
mkdir profil_pictures