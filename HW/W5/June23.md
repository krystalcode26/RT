Solution for distributed transactions has two approaches:
- Two-Phase Commit (2PC)
2PC introduces a dedicated coordinator service that manages the entire transaction across all services. It operates in two phases:

Phase 1 — Preparation:
Each service checks whether its queries can be executed without actually running them.
They verify things like database connectivity, constraint violations, and data existence, then report back to the coordinator. No data is written yet.

Phase 2 — Execution:
Only when all services confirm they are ready does the coordinator send the signal to execute. If any service fails in the preparation phase, the coordinator tells all services to rollback — nothing gets written.
This provides strong consistency but has trade-offs — the coordinator is a single point of failure, and if it goes down, all distributed transactions are blocked.

- Saga: always execution don’t have preparation, execute sequentially
Each service executes immediately and uses compensation logic to undo completed steps if a downstream step fails. -- eventual consistency

Choreography style (decentralized, Eventlistener): decentralized independent 
No central controller. Each service reacts to events independently and publishes the next event.
If fail need to undo the logic, fail rollback (sql: compensation dynamic template delete the employee based on id) and send signal and rollback to the root server
Chaining requests

Orchestration style (centralized control flows, see every step): 
Orchestrator controls the entire flow, explicitly tell each service what to do and waiting for confirmation before moving to the next step.

In choreography, there is no central brain — each service knows what to do when it receives an event and publishes the next event on its own. The flow emerges from the services reacting to each other.
In orchestration, there is a single coordinator (the orchestrator) that tells each service what to do, waits for the response, and decides the next step. The flow is explicitly defined in one place.
