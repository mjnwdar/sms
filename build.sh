#! /bin/bash

DIR="$( cd -P "$( dirname "$0" )" && pwd )"

function build() {
    for file in `ls $1 | sort -r`
    do
        if [[ -d "$1/$file" ]] && [[ -f "$1/$file/pom.xml" ]]; then
            cd "$1/$file" && mvn clean install -Dmaven.test.skip=true
        fi
    done
}

echo "$DIR"

build $DIR