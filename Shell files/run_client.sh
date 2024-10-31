CLIENT_IMAGE='project1-client-image'
PROJECT_NETWORK='project1-network'
SERVER_CONTAINER='my-server'

if [ $# -ne 3 ]
then
  echo "Usage: ./run_client.sh <container-name> <port-number> <protocol>"
  exit
fi

# run client docker container with cmd args
docker run -it --rm --name "$1" \
 --network $PROJECT_NETWORK $CLIENT_IMAGE \
 java client.ClientApp "$3" $SERVER_CONTAINER "$2"
