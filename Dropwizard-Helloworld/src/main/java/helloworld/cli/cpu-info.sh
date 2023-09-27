#!/bin/bash

# Define the output file
output_file="../../../resources/assets/CPUs.txt"
cpu_info_file="../../../../../../../../../../proc/cpuinfo"

# Check if /proc/cpuinfo exists
if [ -e $cpu_info_file ]; then
    # Initialize the CPU information file
    > "$output_file"

    # Read /proc/cpuinfo and save it to the output file
    while IFS= read -r line; do
        echo "$line" >> "$output_file"
    done < "/proc/cpuinfo"

    echo "CPU information saved to $output_file"
else
    echo "/proc/cpuinfo not found"
fi

# /proc/...

# /home/pc-724/Documents/projects/Dropwizard-Helloworld/src/main/resources/assets
