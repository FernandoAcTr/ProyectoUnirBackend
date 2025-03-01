#!/bin/bash

# Navigate to the "business" directory
cd business || { echo "Directory 'business' not found!"; exit 1; }

# Iterate over all subdirectories
for dir in */ ; do
    if [ -d "$dir" ]; then
        echo "Entering directory: $dir"
        cd "$dir" || continue

        # Run Maven build
        echo "Running 'mvn clean package' in $dir"
        mvn clean package

        # Go back to the business directory
        cd ..
    fi
done

echo "Build process completed for all projects."
