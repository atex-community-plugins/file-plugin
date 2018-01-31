File plugin
================================

This is the deploy guide.

### (Re)Deploy an existing release

```
mvn clean install site deploy -Pdeploy
```

### Prepare for a release

First you need to generate the release notes, use the last tag generated:

```
./getReleaseNotes.sh  xxxx.yyyy | pbcopy
```

Edit the file `releaseNotes.md` by adding the new release on top and pasting the output of the script above,
update `README.md` with the version that will be released (i.e. in the dependency examples) and then push to git.

```
git commit -a -m "Update release notes for xxxx.yyyy"
git push
```

```
mvn clean install site deploy release:prepare -Pdeploy -DautoVersionSubmodules=true
mvn clean install site deploy release:perform -Pdeploy
```

### Cleanup a broken release

Sometimes when you perform a release it may fails with an error `Unable to tag SCM`.

You need to clean the release

```
mvn release:clean
```

and remove the tag from the local and remote git:

```
git tag -d xxxx.yyyy
git push --delete origin xxxx.yyyy
```

finally you can cleanup the checked out files (this will revert all the modified files!!!):

```
git reset HEAD --hard
```

Then you can try to release it again.

