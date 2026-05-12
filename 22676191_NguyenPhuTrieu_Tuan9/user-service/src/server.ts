import cors from "cors";
import dotenv from "dotenv";
import express, { Request, Response } from "express";

dotenv.config();

type User = {
  id: string;
  name: string;
  email: string;
  password: string;
};

const users: User[] = [
  { id: "u1", name: "Nguyen Van An", email: "an@example.com", password: "123456" },
  { id: "u2", name: "Tran Thi Binh", email: "binh@example.com", password: "123456" }
];

const app = express();
const port = Number(process.env.PORT ?? 8081);
const host = process.env.HOST ?? "0.0.0.0";

app.use(cors());
app.use(express.json());

app.get("/health", (_req: Request, res: Response) => {
  res.json({ service: "user-service", status: "UP" });
});

app.post("/login", (req: Request, res: Response) => {
  const { email, password } = req.body as { email?: string; password?: string };
  const user = users.find((item) => item.email === email && item.password === password);

  if (!user) {
    return res.status(401).json({ message: "Email hoac mat khau khong dung" });
  }

  const { password: _password, ...safeUser } = user;
  return res.json({ message: "Dang nhap thanh cong", user: safeUser });
});

app.get("/users/:id", (req: Request, res: Response) => {
  const user = users.find((item) => item.id === req.params.id);

  if (!user) {
    return res.status(404).json({ message: "Khong tim thay user" });
  }

  const { password: _password, ...safeUser } = user;
  return res.json(safeUser);
});

app.listen(port, host, () => {
  console.log(`User Service running at http://${host}:${port}`);
});

