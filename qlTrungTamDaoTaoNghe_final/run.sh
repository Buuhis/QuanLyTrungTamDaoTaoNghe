#!/bin/bash

# Tạo thư mục bin nếu chưa tồn tại
mkdir -p bin

# Biên dịch tất cả các file .java
javac -cp lib/jcalendar-1.4.jar -d bin src/test/Test.java src/controller/*.java src/model/*.java src/util/*.java src/view/*.java

# Kiểm tra xem biên dịch có thành công không
if [ $? -eq 0 ]; then
    echo -e "\033[0;32mCompile success!\033[0m"
    # Chạy chương trình
    java -cp bin:lib/jcalendar-1.4.jar test.Test
else
    echo -e "\033[0;31mCompilation failed! Please check for errors.\033[0m"
fi
