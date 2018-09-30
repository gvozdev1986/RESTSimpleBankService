# RESTSimpleBankService
Service simulating the operation of a bank server (service).

Service (Server) simulates the operation of the banking system and has the ability to:

1. Getting the balance.
2. Write off funds from the account.
3. Deposit account.

Notes for request:
1. Each parameters for request should be encoded;
2. Encoding parameters should be in client for security;
3. Key for decoding and encoding should be save in an inaccessible place.


Table with examples of queries:
<table>
    <tr>
        <td>#</td>
        <td>Brief</td>
        <td>Type of query</td>
        <td>Example query</td>
        <td>Parameters</td>
        <td>Success response</td>
        <td>Unsuccess response</td>
    </tr>
    <tr>
        <td>1</td>
        <td>Get token</td>
        <td>GET</td>
        <td>/RESTServer/rest/auth/</td>
        <td>
            <ol>
                <li>creditCardNumber</li>
                <li>cvCode</li>
                <li>firstName</li>             
                <li>lastName</li>
                <li>monthValid</li>
                <li>yearValid</li>
            </ol>
        </td>
        <td>7d0d53cc-24f7-4d53-b482-c29afb939044</td>
        <td>not found account</td>
    </tr>        
    <tr>
        <td>2</td>
        <td>Get balance</td>
        <td>GET</td>
        <td>/RESTServer/rest/balance/</td>
        <td>
            <ol>
                <li>tokenRest</li>
                <li>cardNumber</li>
            </ol>
        </td>
        <td>
        ```xml
            <balance>
                <cardNumber>1111 1111 1111 1111</cardNumber>
                <balanceBankAccount>1179969.61</balanceBankAccount>
            </balance>
        ```
        </td>
        <td>Empty response</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Write-off money from bank account</td>
        <td>PUT</td>
        <td>/RESTServer/rest/balance/write/</td>
        <td>
            <ol>
                <li>tokenRest</li>
                <li>cardNumber</li>
                <li>amount</li>
                <li>cvCode</li>
            </ol>
        </td>
        <td>
        ```xml
            <response>
                <status>true</status>
                <message>Operation was successful.</message>
            </response>
        ```
        </td>
        <td>
        ```xml
            <response>
                <status>false</status>
                <message>Operation wasn't successful.</message>
            </response>
         ```
        </td>
    </tr>
    <tr>
        <td>4</td>
        <td>Refill (back) money to bank account</td>
        <td>PUT</td>
        <td>/RESTServer/rest/balance/refill/</td>
        <td>
            <ol>
                <li>tokenRest</li>
                <li>cardNumber</li>
                <li>amount</li>
                <li>cvCode</li>
            </ol>
        </td>
        <td>
        ```xml
            <response>
                <status>true</status>
                <message>Operation was successful.</message>
            </response>
        ```
        </td>
        <td>
        ```xml
            <response>
                <status>false</status>
                <message>Operation wasn't successful.</message>
            </response>
        ```
        </td>
    </tr>
    <tr>
        <td>5</td>
        <td>Verification credit card</td>
        <td>GET</td>
        <td>/RESTServer/rest/balance/check/card/</td>
        <td>
            <ol>
                <li>tokenRest</li>
                <li>cardNumber</li>
                <li>cvCode</li>
            </ol>
        </td>
        <td>
        ```xml
            <response>
                <status>true</status>
                <message>Credit card has been successful checked.</message>
            </response>
        ```
        </td>
        <td>
        ```xml
            <response>
                <status>false</status>
                <message>Credit card hasn't been checked.</message>
            </response>
        ```
        </td>
    </tr>
</table>

Description:
<ol>
    <li><b>creditCardNumber</b> - Credit card number in format XXXX XXXX XXXX XXXX</li>
    <li><b>cvCode</b> - Secret code from credit card for verification</li>
    <li><b>firstName</b> - First name owner credit card (from card)</li>             
    <li><b>lastName</b> - Last name owner credit card (from card)</li>
    <li><b>monthValid</b> - Month valid credit card in format XX</li>
    <li><b>yearValid</b> - Year valid credit card in format XX</li>
    <li><b>amount</b> - Amount of payment (write-off, refill, back) balance</li>
</ol>
