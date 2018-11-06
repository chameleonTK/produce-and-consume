#!/usr/bin/env bash

cp ${TESTDIR}/Test.java .
javac Test.java
# java Test | tee ${TESTDIR}/expected.out
java Test
rm -f Test.java
