# Cleanup Instructions: Remove Duplicate Root Files

This branch documents which files should be deleted from the root directory to complete the monorepo reorganization.

## Files to Delete from Root (They belong only in `inventra-backend/`)

The following files exist at BOTH root and in `inventra-backend/`. Keep only the ones in the subdirectory:

### 1. **pom.xml** (Root Level)
   - **Delete:** `./pom.xml`
   - **Keep:** `./inventra-backend/pom.xml`
   - **Reason:** Maven build config is backend-specific

### 2. **mvnw & mvnw.cmd** (Root Level)
   - **Delete:** `./mvnw`, `./mvnw.cmd`
   - **Keep:** `./inventra-backend/mvnw`, `./inventra-backend/mvnw.cmd`
   - **Reason:** Maven wrapper scripts are backend build tools

### 3. **.mvn/** (Root Level)
   - **Delete:** `./.mvn/`
   - **Keep:** `./inventra-backend/.mvn/`
   - **Reason:** Maven configuration directory is backend-specific

### 4. **src/** (Root Level)
   - **Delete:** `./src/` and all contents
   - **Keep:** `./inventra-backend/src/`
   - **Reason:** Application source code belongs in backend

### 5. **API_TEST_GUIDE.md** (Root Level)
   - **Delete:** `./API_TEST_GUIDE.md`
   - **Keep:** `./inventra-backend/API_TEST_GUIDE.md`
   - **Reason:** API testing guide is backend documentation

### 6. **README_BUILD.md** (Root Level)
   - **Delete:** `./README_BUILD.md`
   - **Keep:** `./inventra-backend/README_BUILD.md`
   - **Reason:** Build instructions are backend-specific

## How to Delete via GitHub UI

1. Navigate to each file on GitHub
2. Click the "..." menu → "Delete this file"
3. Commit with message: `"Remove duplicate root-level file: [filename]"`

## How to Delete via Git CLI (Local)

```bash
git checkout cleanup/remove-duplicate-root-files
git rm pom.xml mvnw mvnw.cmd API_TEST_GUIDE.md README_BUILD.md
git rm -r .mvn src
git commit -m "Remove duplicate root-level files

- Deleted: pom.xml, mvnw, mvnw.cmd, .mvn/, src/, API_TEST_GUIDE.md, README_BUILD.md
- These files belong only in inventra-backend/
- Clarifies monorepo structure"
git push origin cleanup/remove-duplicate-root-files
```

## After Cleanup - Repository Structure

```
inventra-backend/
  ├── pom.xml                    ✓ Maven config
  ├── mvnw / mvnw.cmd            ✓ Maven wrapper
  ├── .mvn/                       ✓ Maven settings
  ├── src/                        ✓ Java source code
  ├── API_TEST_GUIDE.md           ✓ Backend API docs
  ├── README_BUILD.md             ✓ Build instructions
  └── README.md                   ✓ Backend-specific README

inventra-frontend/
  ├── package.json                ✓ npm config
  ├── vite.config.js              ✓ Vite bundler config
  ├── src/                        ✓ React source code
  └── README.md                   ✓ Frontend-specific README

.gitignore                         ✓ Monorepo-level ignores
README.md                          ✓ Project overview
```

## Status

- ✅ Created comprehensive root README
- ✅ Updated .gitignore for monorepo structure
- ⏳ **Pending:** Delete duplicate files (complete the PR)
