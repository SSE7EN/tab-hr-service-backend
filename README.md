# tab-hr-service-backend

### Installation guide:

## Clone repository
with bash, from web browser, however you want


## Install docker
probably using that link to install docker engine: https://docs.docker.com/engine/install/
then install docker compose: https://docs.docker.com/compose/install/

## Run docker
```
docker-compose up -d
```
If you have postgres already installed and running - stop it and try again

## Run backend
`./gradlew run`
or on windows probably
`gradlew.bat run`
(remember to be in project directory)


## Check api
go to http://localhost:8080/swagger-ui/index.html
there you have apis names, when you click on it you have url and method, example response.
to the right you have try it out, click on it, then execute and you will have response to your request. <br/>
In some requests you can change values in inputs and stuff, edit it as you need


## Authorization in /swagger-ui/
first use magic curl:
```
curl --location --request POST 'http://localhost:8080/oauth/token?grant_type=password&username=test&password=test_password' --header 'Authorization: Basic aW9wcm9qZWN0LWNsaWVudDppb3Byb2plY3Qtc2VjcmV0' --data-raw ''
```
or however it works on windows, alternatively login in frontend and<br/>
press `ctrl+shift+i`, go to `storage` -> `local storage` -> `http://localhost:3000` -> look for key `myToken` -> copy `Value` (sth like `Bearer some_garbage`)<br/>
<br/>
Now go to http://localhost:8080/swagger-ui/ <br/>
find green `Authorize` button on the right, press it, and in `Value` input paste that `Bearer some_garbage` you copied before <br/>
click `Authorize` and you should be able to test api as logged in user.