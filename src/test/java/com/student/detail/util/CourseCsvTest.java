package com.student.detail.util;

import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.student.detail.model.Course;
import com.student.detail.util.CsvFileLodderCourse;

class CourseCsvTest {

    @Test
    void testLoadCourseData() {
        String filePath = "D:\\sample\\Coursedata.csv"; // Adjust the file path as necessary
        Map<Integer, Course> courses = CsvFileLodderCourse.loadCourseData(filePath);

        // Assert that the courses map is not null and not empty
        assertNotNull(courses, "Courses map should not be null");
        assertFalse(courses.isEmpty(), "Courses map should not be empty");

        // Optional: Print the unsorted list of courses for manual verification
        System.out.println("Courses (Unsorted):");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }
}
