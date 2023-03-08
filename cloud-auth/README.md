thanks to the https://github.com/macrozheng/mall-learning/blob/master/docs/cloud/gateway_oauth2.md
1. start eureka server
2. start cloud-auth2-api auth gateway

framework creates the endpoints

```java
@RequestMapping(value = "/oauth/token", method=RequestMethod.GET)
public ResponseEntity<OAuth2AccessToken> getAccessToken(Principal principal, @RequestParam
	Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ...
        }
```
in org.springframework.security.oauth.spring-security-oauth2 class TokenEndpoint

when you start server there are 3 endpoints Will secure Or [Ant [pattern='/oauth/token'], Ant [pattern='/oauth/token_key'], Ant [pattern='/oauth/check_token']]

Use command
```shell
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
```
to generate jwt.jks file.