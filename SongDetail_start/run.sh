#!/usr/bin/env bash

./gradlew \
  --parallel \
  --offline \
  -Pnolint \
  -Pnorelease \
  --continuous \
  --profile \
  :app:startDev
#  --build-cache \
#  --configure-on-demand \
