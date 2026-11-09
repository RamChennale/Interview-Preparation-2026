# Build a Simple Q&A Chatbot That Answers From Your Own Data

What you're building is called **RAG (Retrieval-Augmented Generation)**. It's the most common beginner-friendly AI agent pattern.

## How it works

1. **Prepare your data:** split your documents into small chunks.
2. **Index it:** convert each chunk into numbers (embeddings) and store them in a vector database.
3. **Retrieve:** when the user asks a question, find the 3 to 4 most relevant chunks.
4. **Generate:** send the question and those chunks to an LLM (Claude) with the instruction "answer only from this context."

The LLM never needs to be trained on your data. You hand it the relevant pieces at question time.

**Tech stack (all beginner-friendly):**
- Python 3.10+
- `sentence-transformers` for free, local embeddings
- `chromadb` as a local vector database (no server needed)
- Anthropic API (Claude) for the answers

---

## Step 1: Install prerequisites

1. Install **Python 3.10 or higher** from python.org. Verify with `python --version`.
2. Install an editor (VS Code or PyCharm).
3. Get an **Anthropic API key**: sign up at console.anthropic.com, add a small amount of credit, then create a key under *API Keys*. Copy it somewhere safe. You only see it once.

## Step 2: Create the project and virtual environment

```bash
mkdir my-rag-bot
cd my-rag-bot
python -m venv venv
```

Activate it:
- **Windows:** `venv\Scripts\activate`
- **Mac/Linux:** `source venv/bin/activate`

You should see `(venv)` in your terminal. A virtual environment keeps this project's libraries separate from everything else on your machine.

## Step 3: Install libraries

```bash
pip install anthropic chromadb sentence-transformers
```

This takes a few minutes the first time because it downloads machine-learning libraries.

## Step 4: Set your API key

Never hardcode the key in your code. Set it as an environment variable:

- **Mac/Linux:** `export ANTHROPIC_API_KEY="your-key-here"`
- **Windows (PowerShell):** `$env:ANTHROPIC_API_KEY="your-key-here"`

The Anthropic library reads this variable automatically.

## Step 5: Add your data

Create a folder called `data` and put in a few `.txt` files. For a first test, use something small where you know the answers, such as an FAQ, a product description, or notes on a topic you know well.

Your structure should look like this:

```
my-rag-bot/
├── data/
│   ├── faq.txt
│   └── policies.txt
├── ingest.py
└── chatbot.py
```

Start with plain text. Once this works, you can extend it to PDFs and other formats.

## Step 6: Write the ingestion script (`ingest.py`)

This script reads your files, chunks them, creates embeddings, and stores them.

```python
import os
import glob
import chromadb
from sentence_transformers import SentenceTransformer

DATA_DIR = "data"
DB_DIR = "chroma_db"
CHUNK_SIZE = 800     # characters per chunk
OVERLAP = 150        # characters shared between neighbouring chunks

def load_documents():
    docs = []
    for path in glob.glob(os.path.join(DATA_DIR, "*.txt")):
        with open(path, encoding="utf-8") as f:
            docs.append((os.path.basename(path), f.read()))
    return docs

def chunk_text(text, size=CHUNK_SIZE, overlap=OVERLAP):
    chunks = []
    start = 0
    while start < len(text):
        chunks.append(text[start:start + size])
        start += size - overlap
    return chunks

def main():
    print("Loading embedding model (first run downloads it)...")
    model = SentenceTransformer("all-MiniLM-L6-v2")

    client = chromadb.PersistentClient(path=DB_DIR)
    try:
        client.delete_collection("knowledge")   # start fresh on each run
    except Exception:
        pass
    collection = client.create_collection("knowledge")

    ids, texts, metas = [], [], []
    for filename, content in load_documents():
        for i, chunk in enumerate(chunk_text(content)):
            ids.append(f"{filename}-{i}")
            texts.append(chunk)
            metas.append({"source": filename})

    if not texts:
        print("No .txt files found in the data folder.")
        return

    embeddings = model.encode(texts).tolist()
    collection.add(ids=ids, documents=texts, embeddings=embeddings, metadatas=metas)
    print(f"Indexed {len(texts)} chunks.")

if __name__ == "__main__":
    main()
```

**Why chunk?** An LLM answers better when you give it only the relevant paragraphs rather than a whole book. The overlap prevents a sentence from being cut in half and losing meaning.

Run it:

```bash
python ingest.py
```

You should see `Indexed N chunks.` Re-run this script whenever you change your data.

## Step 7: Write the chatbot (`chatbot.py`)

```python
import anthropic
import chromadb
from sentence_transformers import SentenceTransformer

DB_DIR = "chroma_db"
TOP_K = 3
MODEL_NAME = "claude-sonnet-5-5"

embedder = SentenceTransformer("all-MiniLM-L6-v2")
collection = chromadb.PersistentClient(path=DB_DIR).get_collection("knowledge")
llm = anthropic.Anthropic()   # reads ANTHROPIC_API_KEY

SYSTEM_PROMPT = (
    "You are a helpful assistant. Answer the user's question using ONLY the "
    "context provided. If the answer is not in the context, say: "
    "'I don't have that information in my knowledge base.' "
    "Do not make up facts. Keep answers clear and concise."
)

def retrieve(question):
    q_embedding = embedder.encode([question]).tolist()
    results = collection.query(query_embeddings=q_embedding, n_results=TOP_K)
    return results["documents"][0], results["metadatas"][0]

def answer(question):
    chunks, metas = retrieve(question)
    context = "\n\n---\n\n".join(chunks)

    user_message = f"Context:\n{context}\n\nQuestion: {question}"

    response = llm.messages.create(
        model=MODEL_NAME,
        max_tokens=500,
        system=SYSTEM_PROMPT,
        messages=[{"role": "user", "content": user_message}],
    )
    sources = sorted({m["source"] for m in metas})
    return response.content[0].text, sources

def main():
    print("Chatbot ready. Type 'quit' to exit.\n")
    while True:
        question = input("You: ").strip()
        if question.lower() in ("quit", "exit"):
            break
        if not question:
            continue
        text, sources = answer(question)
        print(f"\nBot: {text}")
        print(f"(Sources: {', '.join(sources)})\n")

if __name__ == "__main__":
    main()
```

Run it:

```bash
python chatbot.py
```

## Step 8: Test it properly

Since you come from a testing background, treat this like a test suite. Write 10 to 15 questions in three groups:

1. **Answerable questions:** the answer is clearly in your data. Does the bot get it right?
2. **Unanswerable questions:** the answer is not in your data. The bot should say "I don't have that information." If it answers anyway, it is hallucinating.
3. **Tricky phrasing:** the same question worded differently, to check that retrieval still finds the right chunk.

Record the results in a spreadsheet: question, expected answer, actual answer, pass or fail.

## Step 9: Debug when answers are wrong

Most problems are in retrieval, not the LLM. Add this temporarily inside `answer()` to see what was retrieved:

```python
print("RETRIEVED CONTEXT:\n", context)
```

| Symptom | Likely cause | Fix |
|---|---|---|
| Says "don't have info" but it's in your data | The right chunk wasn't retrieved | Increase `TOP_K` to 5, or adjust chunk size |
| Answers are cut off or lack detail | Chunks too small | Raise `CHUNK_SIZE` to 1200 |
| Answer mixes unrelated topics | Chunks too large | Lower `CHUNK_SIZE` to 500 |
| Makes up facts | Prompt too loose | Strengthen the system prompt |

After changing chunk settings, re-run `ingest.py`.

## Step 10: Add conversation memory (optional upgrade)

Right now each question is independent. To support follow-ups like "what about the second one?", keep a history list and pass it in `messages`:

```python
history = []

def answer(question):
    chunks, metas = retrieve(question)
    context = "\n\n---\n\n".join(chunks)
    history.append({"role": "user",
                    "content": f"Context:\n{context}\n\nQuestion: {question}"})
    response = llm.messages.create(
        model=MODEL_NAME, max_tokens=500,
        system=SYSTEM_PROMPT, messages=history,
    )
    reply = response.content[0].text
    history.append({"role": "assistant", "content": reply})
    return reply, sorted({m["source"] for m in metas})
```

## Step 11: Add a web UI (optional upgrade)

```bash
pip install streamlit
```

Create `app.py`:

```python
import streamlit as st
from chatbot import answer

st.title("My Knowledge Bot")

if "messages" not in st.session_state:
    st.session_state.messages = []

for m in st.session_state.messages:
    with st.chat_message(m["role"]):
        st.write(m["content"])

if question := st.chat_input("Ask a question"):
    st.session_state.messages.append({"role": "user", "content": question})
    with st.chat_message("user"):
        st.write(question)
    text, sources = answer(question)
    reply = f"{text}\n\n*Sources: {', '.join(sources)}*"
    st.session_state.messages.append({"role": "assistant", "content": reply})
    with st.chat_message("assistant"):
        st.write(reply)
```

Run with `streamlit run app.py`. It opens in your browser.

## Step 12: Learning roadmap after this

1. **Load more file types:** PDFs (`pypdf`), Word files (`python-docx`), web pages.
2. **Use a framework:** try LangChain or LlamaIndex to see how they wrap the steps you just wrote by hand. Doing it manually first means you will understand what they do.
3. **Better retrieval:** add re-ranking, hybrid search (keyword + vector), and metadata filtering.
4. **Systematic evaluation:** automate your Step 8 test set so you can rerun it after every change.
5. **Agent behavior:** give the LLM tools (search, calculator, API calls) so it can decide when to look something up, which is what makes it a true agent.
6. **Deploy:** wrap it in FastAPI and host it, or use Streamlit Community Cloud.

---

## Common beginner errors

- **`AuthenticationError`:** the API key isn't set in the *same terminal* you're running from.
- **`collection knowledge does not exist`:** you haven't run `ingest.py` yet.
- **Slow first run:** the embedding model downloads once (about 90 MB), then it's cached.
- **Changed data but the bot shows old answers:** re-run `ingest.py`.

If you tell me what kind of data you want it to answer from (FAQ, PDFs, your own notes), I can adapt the loading step for it, or I can put this whole guide into a downloadable document if you'd like to keep it.