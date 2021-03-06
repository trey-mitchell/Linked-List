public class LinkedList 
{
	private Node head;
	private int count;
	private Node tail;
	
	public LinkedList()
	{
		this.head = null;
		this.count = 0;
		this.tail = null;	
	}
	
	
	public int get(int index)
	{
		int count = this.count();
		if(index >= count || index < 0)
		{
			System.out.println("Illegal Index");
			return -1;
			//This will eventually need to return an excpeti0n.
		}
		else
		{
			Node curr = head;
			for(int i = 0; i < index; i++)
			{
				curr = curr.getNextNode();
			}
			return curr.getPayload();
		}
	}
	
	public int count()
	{
		return this.count;
	}
	
	public void display()
	{
		if(head == null)
		{
			System.out.println("Empty List");
		}
		else
		{
			Node currNode = this.head;
			while(currNode.getNextNode() != null)
			{
				System.out.print(currNode.getPayload() + " -> ");
				currNode = currNode.getNextNode();
			}
			System.out.println(currNode.getPayload() + " -> null");
		}
	}
	
	public void addAtIndex(int payload, int index)

	{
		if(index <= 0)
		{
			this.addFront(payload);
		}
		else if(index >= count)
		{
			this.addEnd(payload);
		}
		else
		{
			Node n = new Node(payload);
			Node prevTarget = null;
			Node fronTarget = this.head;
			
			for(int i = 0; i < index; i++)
			{
				prevTarget = fronTarget;
				fronTarget = fronTarget.getNextNode();
			}
			n.setNextNode(fronTarget);
			n.setPrevNode(prevTarget);
			fronTarget.setPrevNode(n);
			prevTarget.setNextNode(n);
			count++;
		}
	}

	public void addFront(int payload)
	//needs update
	{
		Node n = new Node(payload);
		if(head == null)
		{
			this.head = n;
			this.tail = n;
		}
		else
		{
			n.setNextNode(head);
			head.setPrevNode(n);
			this.head = n;
		}
		this.count++;
	}
	
	public void addEnd(int payload)
	{
		Node n = new Node(payload);
		if(this.head == null)
		{
			this.head = n;
		}
		else
		{
			this.tail.setNextNode(n);
			n.setPrevNode(tail);
		}
		this.tail = n;
		this.count++;
	}
	
	public int removeFront() throws Exception
	{
		if(head==null)
			{
			throw new Exception("The list is empty.");
			}
		Node curr = this.head;
		head=head.getNextNode();
		head.setPrevNode(null);
		//new line
		curr.setNextNode(null);
		this.count--;
		return curr.getPayload();
	}
	
	public int removeEnd() throws Exception
	{
		if(head==null)
		{
			throw new Exception("Cannot Remove End: Empty List");
		}
		else if(this.count==1)
		{
			return this.removeFront();
		}
		Node curr = this.tail;
		this.tail = this.tail.getPrevNode();
		tail.setNextNode(null);
		curr.setPrevNode(null);
		count--;
		return curr.getPayload();
	}
	
	public int removeAtIndex(int index) throws Exception
	{
		if(head==null)
		{
			throw new Exception("Cannot Remove from Index: Empty List");
		}	
		else if(this.count==1)
		{
			return this.removeFront();
		}
		else if(index >= count)
		{
			throw new Exception("Cannot Remove from Index: Index too Large");
		}
		else if(index < 0)
		{
			throw new Exception("Cannot Remove from Index: Index Must be Greater than Zero");
		}
		else if(index == count -1)
		{
			return this.removeEnd();
		}
		//test this part
		//This way nobody has to worry about changing tail
		else
		{
			Node targetLead = null;
			Node target = this.head;
			for(int i = 0; i < index; i++)
			{
				targetLead = target;
				target = target.getNextNode();
			}
			int payload = target.getPayload();
			targetLead.setNextNode(target.getNextNode());
			target.setPrevNode(null);
			(target.getNextNode()).setPrevNode(targetLead);
			target.setNextNode(null);
			this.count--;
			return payload;
		}
}
	public void displayInReverse()
	{
		Node n = this.tail;
		for(int i = 0; i < count; i++)
		{
			System.out.print(n.getPayload() + " ");
			System.out.print("-> ");
			n = n.getPrevNode();
		}
		System.out.print("null");
	}
}
/*Add a pointer called tail that points to the end of the list.  
 * Each Node knows about the previous node.
Update all add and remove methods, then rewrite displayInReverse() to take advantage of new
double-linked list*/