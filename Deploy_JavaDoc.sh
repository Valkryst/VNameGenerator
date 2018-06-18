#!/bin/bash

PROJECT_NAME="VNameGenerator"

# Display Repo:
echo -e "Repo Slug:"
echo -e "\tExpected: Valkryst/${PROJECT_NAME}"
echo -e "\tActual: $TRAVIS_REPO_SLUG\n"

# Display JDK Version:
echo -e "JDK Version:"
echo -e "\tExpected: oraclejdk8"
echo -e "\tActual: $TRAVIS_JDK_VERSION\n"

# Display 'Is Pull Request':
echo -e "Is Pull Request:"
echo -e "\tExpected: false"
echo -e "\tActual: $TRAVIS_PULL_REQUEST\n"

# Display Branch:
echo -e "Branch:"
echo -e "\tExpected: master"
echo -e "\tActual: $TRAVIS_BRANCH\n"

if [ "$TRAVIS_REPO_SLUG" == "Valkryst/"${PROJECT_NAME} ] &&
   [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] &&
   [ "$TRAVIS_PULL_REQUEST" == "false" ] &&
   [ "$TRAVIS_BRANCH" == "master" ]; then

    echo -e "Publishing JavaDoc...\n"

    cd $HOME
    git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/Valkryst/${PROJECT_NAME} gh-pages > /dev/null

    cd gh-pages
    git rm -r *
    git config user.name "Valkryst"
    git config user.email "valkryst@valkryst.com"
    cp -r /home/travis/build/Valkryst/${PROJECT_NAME}/target/site/apidocs/* .
    git add .
    git commit -m "Updates JavaDoc on successful Travis CI build. Build #$TRAVIS_BUILD_NUMBER auto-pushed to gh-pages."
    git push -fq origin gh-pages > /dev/null

    echo -e "Published JavaDoc to gh-pages branch.\n"
fi
