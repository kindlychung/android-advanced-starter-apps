#!/usr/bin/env bash

./gradlew \
  --build-cache \
  --configure-on-demand \
  --parallel \
  --offline \
  -x lint -x lintVitalDevRelease \
  --continuous \
  :app:startDev
