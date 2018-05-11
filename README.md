# Product Research & Development (PRD)

## Overview

API component(s) for a product R&D proposal management system. 
Note that this is intended to provide a relatively minimal set of functionality,
and primarily serves to demonstrate web service integration in a larger application.

## Functionality

  * Maintain persistent records of comments, feedback, and Level of Effort (LOE) projections 
  relevant to all versions of a proposal, throughout its lifecycle, for auditing and tracking 
  purposes.

  * Maintain persistent records of proposals and any updates to them, sorted by version.

#### Available Routes:

`POST /proposal/new`: create a new Proposal

  * ```json

    ```

`PUT /proposal/{id}`: update an existing Proposal (creates a new version)

  * ```json
  
    ```
    
`GET /proposal/{id}`: retrieve all versions of a given Proposal

  * ```json
  
    ```
  * We will also want to provide functionality to retrieve just the most recent version of 
  a given proposal
  
  * We may also want to provide functionality to retrieve a specific version of a proposal, 
  but this is currently out of scope.
