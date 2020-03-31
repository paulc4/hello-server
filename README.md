# Hello Server

You will need Docker and Java JDK installed to run this sdemo.

* OpenJDK is available here: https://www.azul.com/downloads/zulu.
  * Scroll down to _Zulu CommunityTM Downloads_
  * Enter Java version, your platform and processor size.

* [Docker for Desktop](https://www.docker.com/products/docker-desktop) is an easy option for Windows o MAcOS and has a one-click option to run Kubernetes as well.

## Run as an Application

1. To build:

   ```sh
   Windows:   mvnw package
   Linux:     ./mvnw package
    ```

1. Should create an executable jar in `target/`.  Run it:

   ```sh
   java -jar target/hello-server-1.0.0.jar
   ```

1. Then either

   * Open your browser to http://localhost:8080 - returns JSON, so best viewed using the JSON add-on for your browser
   * Or, in a second CMD/Terminal window invoke `curl http://localhost:8080`

   **IMPORTANT:** Stop the application once you are satisfied it is working to free up port `8080`.

## Run Using Docker

1. You might like to view the `Dockerfile` first to see what it configures.

1. Create a docker container (where docker-id is your Docker Hub id.  If you don't have one, just use your first name):

   ```sh
   docker build -t <docker-id>/hello-server .
   ```

1. Check it exists - it should be in this list:

   ```sh
   docker images
   ```

1. To run:

   ```sh
   docker run --name hello-server -p8080:8080 <docker-id>/hello-server
   ```

   You should see Spring output to the console as it runs.

1. Again access http://localhost:8080 to check it's working

   **IMPORTANT:** Hit CTRL-C to stop the container to free up port `8080`.

## Run Using Kubernetes

1. To put your image up at Docker Hub, you need to run `docker push <docker-id>/hello-server`.

   * However if you don't have a docker hub account or upoloading the image is too slow, you can use one I created earlier `paulc4/hello-server`.

1. Make sure `kubectl` is pointing at your Kubernetes cluster.

1. Create a dedicated namespace `kubectl create namespace hello`

1. Make it your default: `kubectl config set-context <cluster-name> --namespace=hello`

1. If you wish to use the image you just pushed, edit `hello-server.yaml` and change `paulc4` to your `docker-id`, assuming you called the image `<docker-id>/hello-server`.

1. Deploy the web server: `kubectl apply -f hello-server.yaml`

1. What have we deployed:

   ```sh
   kubectl get pods
   kubectl get deployments
   ```

1. Go to Kubernetes dashboard, select `hello` namespace and view the pods.

1. Expose the web-service: `kubectl expose deployment hello-server --type=LoadBalancer --port=8080 --target-port=8080`

1. To see what IP-address was allocated to the application: `kubectl get services -n paul`. The output looks like:

   ```sh
   $ kubectl get services
   NAME                  CLUSTER-IP   EXTERNAL-IP         PORT(S)    AGE
   hello-server          10.3.245.61  104.155.111.170     8080:32452/TCP   2m
   ```

   The external IP may be a DNS name (for example if your cluster is on AWS).

1. Access `http://<external-ip>:8080`  to check it's working

## Help

### Reference Documentation

For further reference, please consider the following sections:

* [Docker for Desktop](https://www.docker.com/products/docker-desktop)
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Spring Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

