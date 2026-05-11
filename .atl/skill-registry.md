# Skill Registry — e-commerce

> Auto-generated from user-level and project-level skill scans.
> Last updated: 2026-05-10

## Project Context

- **Project**: e-commerce (dropshipping automation orchestrator for LATAM)
- **Stack**: Java (planned Quarkus), backend APIs
- **Workspace**: /home/camuxee/Code/e-commerce
- **New project** — no build files or CI detected yet.

## Available Skills

### work-unit-commits
- **Trigger**: implementation, commit splitting, chained PRs, keeping tests/docs with code.
- **Path**: `~/.config/opencode/skills/work-unit-commits/SKILL.md`
- **Compact Rules**:
  - A commit represents one deliverable behavior/fix/docs unit.
  - Do not split by file type (models → services → tests); split by behavior.
  - Tests and docs belong in the same commit as the code they verify.
  - Every commit should tell a story a reviewer can follow.
  - If SDD forecasts >400 lines, group commits into chained PR slices before implementation.
  - Verify rollback of a commit does not break unrelated work.

### branch-pr
- **Trigger**: creating, opening, or preparing PRs for review.
- **Path**: `~/.config/opencode/skills/branch-pr/SKILL.md`
- **Compact Rules**:
  - Every PR MUST link an approved issue (`Closes/Fixes/Resolves #N`).
  - Every PR MUST have exactly one `type:*` label.
  - Branch names MUST match `^(feat|fix|chore|docs|style|refactor|perf|test|build|ci|revert)\/[a-z0-9._-]+$`.
  - Commit messages MUST match conventional commits regex: `^(build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test)(\([a-z0-9\._-]+\))?!?: .+`.
  - Run shellcheck on modified scripts before PR.
  - Automated checks must pass before merge.

### issue-creation
- **Trigger**: creating GitHub issues, bug reports, or feature requests.
- **Path**: `~/.config/opencode/skills/issue-creation/SKILL.md`
- **Compact Rules**:
  - Blank issues are disabled; always use a template (Bug Report or Feature Request).
  - New issues automatically get `status:needs-review`.
  - A maintainer MUST add `status:approved` before any PR can be opened.
  - Questions go to Discussions, NOT issues.
  - Search existing issues for duplicates before creating.
  - Fill ALL required fields in the chosen template.

### chained-pr
- **Trigger**: PRs over 400 lines, stacked PRs, review slices.
- **Path**: `~/.config/opencode/skills/chained-pr/SKILL.md`
- **Compact Rules**:
  - Split PRs over 400 changed lines unless a maintainer explicitly accepts `size:exception`.
  - Keep each PR reviewable in ≤60 minutes.
  - State start, end, prior dependencies, follow-up work, and out-of-scope in every chained PR.
  - Every child PR must include a dependency diagram marking the current PR with `📍`.
  - In Feature Branch Chain, create a draft/no-merge tracker PR.
  - Treat polluted diffs as base bugs: retarget or rebase until only the current work unit appears.

### comment-writer
- **Trigger**: PR feedback, issue replies, reviews, Slack messages, or GitHub comments.
- **Path**: `~/.config/opencode/skills/comment-writer/SKILL.md`
- **Compact Rules**:
  - Start with the actionable point; do not recap the whole PR first.
  - Sound like a thoughtful teammate, not a corporate bot.
  - Prefer 1–3 short paragraphs or a tight bullet list.
  - Give the technical reason when asking for a change.
  - Comment on the highest-value issue, not every tiny preference.
  - Match thread language (Spanish → Rioplatense/voseo: `podés`, `tenés`, `fijate`, `dale`).
  - Do not use em dashes; use commas, periods, or parentheses instead.

### cognitive-doc-design
- **Trigger**: writing guides, READMEs, RFCs, onboarding, architecture, or review-facing docs.
- **Path**: `~/.config/opencode/skills/cognitive-doc-design/SKILL.md`
- **Compact Rules**:
  - Lead with the answer; put the decision/action/outcome first.
  - Use progressive disclosure: happy path first, then details, edge cases, references.
  - Group related info into small sections; keep flat lists short.
  - Use headings, labels, callouts, and summaries (signposting).
  - Prefer tables, checklists, and examples over prose that must be remembered.
  - In PR docs, state what to review first and what is intentionally out of scope.
  - Link previous and next PR when work is chained.

### judgment-day
- **Trigger**: judgment day, dual review, adversarial review, `juzgar`.
- **Path**: `~/.config/opencode/skills/judgment-day/SKILL.md`
- **Compact Rules**:
  - Load ONLY when the user explicitly asks for Judgment Day or equivalent trigger.
  - Resolve project skills before launching judges: read registry and inject `Project Standards`.
  - Launch two blind judges in parallel with identical target and criteria.
  - Wait for both judges before synthesis; never accept a partial verdict.
  - Classify warnings as `WARNING (real)` only if normal intended use can trigger them.
  - Ask before fixing Round 1 confirmed issues.
  - After any fix agent runs, immediately re-launch both judges in parallel.
  - Terminal states are only `JUDGMENT: APPROVED` or `JUDGMENT: ESCALATED`.
  - After 2 fix iterations with remaining issues, ask the user whether to continue.

### skill-creator
- **Trigger**: new skills, agent instructions, documenting AI usage patterns.
- **Path**: `~/.config/opencode/skills/skill-creator/SKILL.md`
- **Compact Rules**:
  - A skill is a runtime instruction contract for an LLM, not human documentation.
  - Follow `docs/skill-style-guide.md` if it exists; otherwise use inline fallback rules.
  - `description` MUST be one physical line, quoted, YAML-safe, trigger-first, ≤250 chars.
  - Frontmatter MUST include `name`, `description`, `license`, `metadata.author`, `metadata.version`.
  - Body target 180–450 tokens, recommended max 700, hard max 1000.
  - Use imperative instructions, not tutorials or background prose.
  - Supporting material goes in `assets/` or `references/`, not the main skill body.
  - Register new skills in `AGENTS.md` when they are project-level.

### git
- **Trigger**: git, commit, branch, merge, rebase, conventional commits, git workflow.
- **Path**: `~/.config/opencode/skills/git/SKILL.md`
- **Compact Rules**:
  - NEVER commit to `main`/`master` directly — always use a branch.
  - ALWAYS use Conventional Commits: `type(scope): description` in imperative, lowercase, under 72 chars.
  - Types: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`, `perf`, `ci`, `build`.
  - Branch naming: `type/scope-description` (e.g. `feat/products-finder`). Use kebab-case for description.
  - Commit by work unit (one purpose per commit), never by file type.
  - Tests and docs belong in the same commit as the code they verify.
  - Feature into main: squash merge. Hotfix into main: merge commit.

 ### java
- **Trigger**: Java code, Quarkus, Spring Boot, clean code, SOLID, DDD, hexagonal architecture, value objects, TDD.
- **Path**: `~/.config/opencode/skills/java/SKILL.md`
- **Compact Rules**:
  - Structure by bounded context: `{context}/application/`, `{context}/domain/`, `{context}/infrastructure/`.
  - Use cases as folders: `application/find/`, `application/create/` — each with Query + Handler.
  - NO technical suffixes: `ProductNotFound` not `ProductNotFoundException`, `ProductFinder` not `ProductService`, `MercadoPagoClient` not `PaymentAdapter`.
  - Value Objects always: `ProductId`, `ProductPrice`, `Money` — never primitives in domain.
  - TDD first: test → domain interface → application → infrastructure. One use case per MR.
  - Domain is king: no framework annotations in domain. Repository interfaces in domain, implementations in infrastructure.
  - Dependency direction: `infrastructure → domain`. Never `domain → infrastructure` or cross-context infrastructure.
  - Methods ≤20 lines, classes ≤200 lines. Constructor injection only.
  - Exceptions describe the problem: `AccountNotExist`, `InsufficientFunds`, `PaymentDeclined`.
  - Shared concepts (Money, CountryCode) in `shared/domain/`.

### go-testing
- **Trigger**: Go tests, go test coverage, Bubbletea teatest, golden files.
- **Path**: `~/.config/opencode/skills/go-testing/SKILL.md`
- **Compact Rules**:
  - Apply focused Go testing patterns; not relevant for Java/Quarkus stack.
  - Prefer table-driven tests, `t.Parallel()`, and subtests.
  - Use `teatest` for Bubbletea TUI apps and golden files for snapshot testing.
  - Keep test data close to tests; avoid global test state.
  - Coverage target: aim for meaningful coverage on critical paths, not 100% for its own sake.

## Project Conventions

- **Language**: Spanish (Rioplatense/voseo) for user-facing communication; English for code and technical docs.
- **User experience**: 10 years Java backend; prefers lightweight frameworks for small projects.
- **Architecture**: DDD hexagonal — structure by bounded context (`products/`, `orders/`, `payments/`), each with `application/`, `domain/`, `infrastructure/`.
- **Code quality**: Clean code + SOLID. Methods ≤20 lines, classes ≤200 lines. Constructor injection only.
- **Naming**: NO technical suffixes. `ProductNotFound` not `ProductNotFoundException`. `ProductFinder` not `ProductService`. Value Objects always.
- **Workflow**: TDD first. One use case per MR. Iterate skill when user shares new rules.
- **Stack**: Quarkus orchestrator connecting WooCommerce, MercadoPago, CJ Dropshipping/Dropi/Printful, Meta Ads, MercadoLibre APIs.
- **Next step**: bootstrap Quarkus project with Maven/Gradle and configure CI before strict TDD can be enabled.

## Skill Resolution Order

1. Project-level skills (`{project-root}/skills/` or `.agent/skills/`) — none found.
2. User-level skills (`~/.config/opencode/skills/`) — used for all entries above.
