#!/bin/bash

PREVTAG=$1
PROJECTURL="https://github.com/atex-community-plugins/file-plugin"

if [ "x$PREVTAG" == "x" ]; then
  echo "Missing previous tag (i.e. xxxx.yyyy), valid tags are:"
  git tag
  exit 1
fi

git log $PREVTAG..HEAD --pretty=format:"- [view commit](${PROJECTURL}/commit/%H) %s"
