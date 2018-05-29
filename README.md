# Product Research & Development (PRD)

## Overview

API component(s) for a product R&D proposal management system. 
Note that this is intended to provide a relatively minimal set of functionality,
and primarily serves to demonstrate web service integration in a larger application.

## Functionality

  * Maintain persistent records of comments relevant to a proposal, throughout its lifecycle, 
  for auditing and tracking purposes.

#### Available Routes:

`POST /comment/new`: create a new Proposal

`GET /comment/`: return default comment
 
`GET /comment/find/{id}`: retrieve all comments for proposalID
