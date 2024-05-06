#!/bin/bash

license_text='/*
 * Copyright (c) 2024, Alexander Rziha
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */'

# Target directory
target_dir="src"

# Loop through all .java files in the target directory and its subdirectories
find "$target_dir" -type f -name "*.java" | while read file; do

  # Check if file exists
  if [ -f "$file" ]; then
    # Check if license text already exists
    if ! grep -qF "$license_text" "$file"; then
      # Prepend license text
      echo "$license_text" | cat - "$file" > temp_file && mv temp_file "$file"
      echo "License comment added to $file"
    else
      echo "License comment already exists in $file (skipped)"
    fi
  fi
done

echo "Checked all Java files and added the license text"