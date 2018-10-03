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
        <td>Success</td>
    </tr>
    <tr>
        <td>1</td>
        <td>Get token</td>
        <td>POST</td>
        <td>/RESTServer/rest/auth/</td>
        <td>
            <ol>
                <li>Content-Type</li>
                <li>creditCardNumber</li>
                <li>cvCode</li>
                <li>firstName</li>             
                <li>lastName</li>
                <li>monthValid</li>
                <li>yearValid</li>
                <li>appSecretCode</li>
            </ol>
        </td>
        <td>status and message in format JSON</td>
    </tr>     
    <tr>
        <td>2</td>
        <td>Write-off money from bank account</td>
        <td>PUT</td>
        <td>/RESTServer/rest/balance/write/</td>
        <td>
            <ol>
                <li>Content-Type</li>
                <li>tokenRest</li>
                <li>cardNumber</li>
                <li>amount</li>
                <li>cvCode</li>
                <li>appSecretCode</li>
            </ol>
        </td>
        <td>status and message in format JSON</td>
    </tr>
    <tr>
        <td>3</td>
        <td>Refill (back) money to bank account</td>
        <td>PUT</td>
        <td>/RESTServer/rest/balance/refill/</td>
        <td>
            <ol>
                <li>Content-Type</li>
                <li>tokenRest</li>
                <li>cardNumber</li>
                <li>amount</li>
                <li>cvCode</li>
                <li>appSecretCode</li>
            </ol>
        </td>
        <td>status and message in format JSON</td>
    </tr>
    <tr>
        <td>4</td>
        <td>Verification credit card</td>
        <td>POST</td>
        <td>/RESTServer/rest/balance/check/card/</td>
        <td>
            <ol>
                <li>Content-Type</li>
                <li>tokenRest</li>
                <li>cardNumber</li>
                <li>cvCode</li>
                <li>appSecretCode</li>
            </ol>
        </td>
        <td>status and message in format JSON</td>
    </tr>
</table>

Description:
<ol>
    <li><b>Content-Type</b> - application/json (for ger response in format JSON</li>
    <li><b>creditCardNumber</b> - Credit card number in format XXXX XXXX XXXX XXXX</li>
    <li><b>cvCode</b> - Secret code from credit card for verification</li>
    <li><b>firstName</b> - First name owner credit card (from card)</li>             
    <li><b>lastName</b> - Last name owner credit card (from card)</li>
    <li><b>monthValid</b> - Month valid credit card in format XX</li>
    <li><b>yearValid</b> - Year valid credit card in format XX</li>
    <li><b>amount</b> - Amount of payment (write-off, refill, back) balance</li>
    <li><b>appSecretCode</b> - Special code for verify then request went from current client</li>
</ol>
