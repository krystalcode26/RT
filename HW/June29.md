Lambda sending a message to SQS usually takes milliseconds to a few seconds, not 5 minutes. 
If it takes 5 minutes, the delay is usually not because Lambda-to-SQS is slow, 
but because of configuration, retry, timeout, cold start, VPC/network issue, or queue delay settings.

The flow is: event triggers Lambda → Lambda processes request → Lambda calls SQS SendMessage API → message appears in the queue. 
Normally this is very fast, usually under a few seconds. 
If we see around 5 minutes delay, I would first check whether the Lambda function has a timeout/retry issue, 
whether the function is waiting on another downstream service, or whether SQS has DelaySeconds configured.

Common reasons for 5-minute delay:

- SQS Delay Queue or message delay
SQS supports delayed messages. If DelaySeconds is set to 300 seconds, the message will not be visible for 5 minutes.

- Lambda retry behavior
If Lambda fails before sending to SQS, AWS may retry the invocation. The message may only be sent after a later retry succeeds.

- Lambda timeout
If Lambda is doing too much work before sending to SQS and the timeout is close to 5 minutes, the delay may come from business logic,
external API calls, DB calls, or network waiting.

- Lambda inside VPC
If Lambda is inside a VPC and needs to call AWS services without proper NAT Gateway or VPC endpoint, network calls can hang or timeout.

- Cold start
Cold start can add delay, but normally it is seconds, not 5 minutes. So cold start alone usually does not explain 5 minutes.

How to solve:

First, I would add CloudWatch logs and metrics to measure where the delay happens: event received time, before SQS call, after SQS call, and message visible time. Then I would check SQS DelaySeconds, Lambda timeout, retry logs, DLQ, VPC/NAT configuration, and downstream dependency latency.

Best solution:
Send the message to SQS as early as possible in Lambda, and move heavy processing to a separate consumer. 
Also set proper Lambda timeout, use retries carefully, configure DLQ, and use VPC endpoints for SQS if Lambda runs inside a VPC.

Normally, Lambda sending a message to SQS should take milliseconds to a few seconds. If it takes 5 minutes, I would not assume 
Lambda-to-SQS is slow. I would investigate configuration and execution flow, especially SQS delay settings, Lambda retries, timeout, 
VPC networking, or slow downstream calls before the SQS send. 

To solve it, I would add CloudWatch tracing/logs, send the message to SQS earlier, remove unnecessary blocking logic, 
check DelaySeconds, configure DLQ/retry policy, and use proper VPC endpoints or NAT if Lambda is inside a VPC.
