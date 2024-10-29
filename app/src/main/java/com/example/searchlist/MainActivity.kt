package com.example.searchlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    private lateinit var searchEditText: EditText
    private lateinit var studentRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết các thành phần trong layout với biến
        searchEditText = findViewById(R.id.searchEditText)
        studentRecyclerView = findViewById(R.id.studentRecyclerView)

        // Khởi tạo danh sách sinh viên
        studentList = listOf(
            Student("Nguyen Van A", "20211001"),
            Student("Le Thi B", "20211002"),
            Student("Tran Van C", "20202020"),
            Student("Nguyen Thi C", "20201234"),
            Student("Tran Van B", "20221987")
        )

        // Cài đặt RecyclerView
        studentAdapter = StudentAdapter(studentList)
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        studentRecyclerView.adapter = studentAdapter

        // Xử lý ô tìm kiếm
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterList(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        val filteredList = if (query.length > 2) {
            studentList.filter {
                it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
            }
        } else {
            studentList
        }
        studentAdapter.updateList(filteredList)
    }
}