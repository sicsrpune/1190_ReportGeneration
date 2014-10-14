/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-07-22 21:53:01 UTC)
 * on 2014-09-06 at 11:05:16 UTC 
 * Modify at your own risk.
 */

package com.appspot.maulikabd.mresultreport.model;

/**
 * Model definition for ResultGraphMessage.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the mresultreport. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ResultGraphMessage extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("average_marks")
  private java.util.List<ResultMarkMessage> averageMarks;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_class")
  private java.lang.String studentClass;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_div")
  private java.lang.String studentDiv;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_id")
  private java.lang.String studentId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_marks")
  private java.util.List<ResultMarkMessage> studentMarks;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_name")
  private java.lang.String studentName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key("student_sub")
  private java.util.List<ResultSubMessage> studentSub;

  static {
    // hack to force ProGuard to consider ResultSubMessage used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(ResultSubMessage.class);
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ResultMarkMessage> getAverageMarks() {
    return averageMarks;
  }

  /**
   * @param averageMarks averageMarks or {@code null} for none
   */
  public ResultGraphMessage setAverageMarks(java.util.List<ResultMarkMessage> averageMarks) {
    this.averageMarks = averageMarks;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStudentClass() {
    return studentClass;
  }

  /**
   * @param studentClass studentClass or {@code null} for none
   */
  public ResultGraphMessage setStudentClass(java.lang.String studentClass) {
    this.studentClass = studentClass;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStudentDiv() {
    return studentDiv;
  }

  /**
   * @param studentDiv studentDiv or {@code null} for none
   */
  public ResultGraphMessage setStudentDiv(java.lang.String studentDiv) {
    this.studentDiv = studentDiv;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStudentId() {
    return studentId;
  }

  /**
   * @param studentId studentId or {@code null} for none
   */
  public ResultGraphMessage setStudentId(java.lang.String studentId) {
    this.studentId = studentId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ResultMarkMessage> getStudentMarks() {
    return studentMarks;
  }

  /**
   * @param studentMarks studentMarks or {@code null} for none
   */
  public ResultGraphMessage setStudentMarks(java.util.List<ResultMarkMessage> studentMarks) {
    this.studentMarks = studentMarks;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getStudentName() {
    return studentName;
  }

  /**
   * @param studentName studentName or {@code null} for none
   */
  public ResultGraphMessage setStudentName(java.lang.String studentName) {
    this.studentName = studentName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<ResultSubMessage> getStudentSub() {
    return studentSub;
  }

  /**
   * @param studentSub studentSub or {@code null} for none
   */
  public ResultGraphMessage setStudentSub(java.util.List<ResultSubMessage> studentSub) {
    this.studentSub = studentSub;
    return this;
  }

  @Override
  public ResultGraphMessage set(String fieldName, Object value) {
    return (ResultGraphMessage) super.set(fieldName, value);
  }

  @Override
  public ResultGraphMessage clone() {
    return (ResultGraphMessage) super.clone();
  }

}