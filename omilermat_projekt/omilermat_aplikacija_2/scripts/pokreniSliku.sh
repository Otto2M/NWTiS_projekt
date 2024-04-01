#!/bin/bash
NETWORK=omilermat_mreza_1

docker run -it -d \
  -p 8070:8080 \
  --network=$NETWORK \
  --ip 200.20.0.4 \
  --name=omilermat_payara_micro \
  --hostname=omilermat_payara_micro \
  omilermat_payara_micro:6.2023.4 \
  --deploy /opt/payara/deployments/omilermat_aplikacija_2-1.0.0.war \
  --contextroot omilermat_aplikacija_2 \
  --noCluster &

wait
