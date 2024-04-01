#!/bin/bash
echo "DOCKER STOP:"
docker stop omilermat_payara_micro
echo "DOCKER REMOVE:"
docker rm omilermat_payara_micro
echo "DOCKER PRIPREMI:"
./scripts/pripremiSliku.sh
echo "DOCKER POKRENI:"
./scripts/pokreniSliku.sh
