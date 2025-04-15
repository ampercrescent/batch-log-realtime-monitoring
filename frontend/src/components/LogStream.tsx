import React, { useEffect, useState } from "react";

const LogStream: React.FC = () => {
  const [logs, setLogs] = useState<string[]>([]);

  useEffect(() => {
    const eventSource = new EventSource("http://localhost:8080/logs/stream"); // ✅ Spring Boot SSE 엔드포인트

    eventSource.onmessage = (event: MessageEvent) => {
      setLogs((prevLogs) => [...prevLogs, event.data]);
    };

    eventSource.onerror = (error) => {
      console.error("SSE 연결 오류 발생!", error);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h2>실시간 로그 스트림</h2>
      <div style={{ height: "300px", overflowY: "scroll", border: "1px solid #ccc", padding: "10px" }}>
        {logs.map((log, index) => (
          <div key={index} style={{ padding: "5px", borderBottom: "1px solid #ddd" }}>
            {log}
          </div>
        ))}
      </div>
    </div>
  );
};

export default LogStream;