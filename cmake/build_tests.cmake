enable_testing()

# Google's test library
# https://github.com/google/googletest
find_package(GTest CONFIG REQUIRED)

# Selecting test sources
# NOT subdirectory recursive, that would be "GLOB_RECURSIVE" instead
file(GLOB test_sources ${CMAKE_SOURCE_DIR}/test/*.cpp)

# Run tests
foreach(test_source ${test_sources})
    get_filename_component(test_source_name ${test_source} NAME_WE)
    add_executable(${test_source_name} ${test_source} ${SRC})
    target_include_directories(${test_source_name} PUBLIC ${gtest_SOURCE_DIR}/include ${gtest_SOURCE_DIR} ${OpenCV_INCLUDE_DIRS} PRIVATE ${CMAKE_SOURCE_DIR}/include)
    target_link_libraries(${test_source_name} gtest gtest_main ${OpenCV_LIBS} nlohmann_json::nlohmann_json)
    set_target_properties(${test_source_name} PROPERTIES RUNTIME_OUTPUT_DIRECTORY ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/test)
    add_test(${test_source_name} ${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/test/${test_source_name})
endforeach()