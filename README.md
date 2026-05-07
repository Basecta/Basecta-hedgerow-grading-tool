# Basecta — Hedgerow Grading Tool

A web tool for ecologists to grade hedgerow quality from uploaded images.

## Status

Active development. This is the production rebuild that succeeds the
[Basecta prototype](https://github.com/Basecta/Basecta-prototype), with a
narrowed scope informed by validation work with agri-environmental consultants
in early May 2026.

## What it does

Ecologists upload images of hedgerows and assign each a quality grade on a 
1–3 scale:

- **1 — Good**
- **2 — Moderate**
- **3 — Poor**

Graded images are stored alongside their assessment, building a structured 
dataset of hedgerow conditions on Irish farmland.

## Why this scope

The earlier prototype attempted automated detection across hedgerows, 
waterways, and soil. Conversations with consultants made it clear that the 
immediate need was simpler and more concrete: a structured way to capture 
and grade hedgerow imagery, with the human judgement kept in the loop. 
Automation can come later, on top of a dataset that doesn't yet exist.

## Tech stack

- **Backend:** Java 21, Spring Boot 3, Spring Security, JPA/Hibernate
- **Database:** PostgreSQL
- **Frontend:** [TBD]
- **Image storage:** [TBD]

## Background

See the [archived prototype](https://github.com/Basecta/Basecta-prototype) 
for the FastAPI/Next.js exploration that preceded this rebuild.
